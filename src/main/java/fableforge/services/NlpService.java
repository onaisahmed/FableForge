package fableforge.services;

import fableforge.core.SuggestionManager;
import fableforge.core.model.Suggestion;
import fableforge.ui.components.PromptBox;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import org.fxmisc.richtext.InlineCssTextArea;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class NlpService {
    private ContextMenu menu;
    private final JLanguageTool langTool;
    private final InlineCssTextArea textArea;
    private Timer debounceTimer;

    public NlpService(PromptBox textArea) {
        this.textArea = textArea;

        // Hide context menu when clicking elsewhere
        textArea.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (menu != null && menu.isShowing()) {
                        menu.hide();
                    }
                });
            }
        });

        // Initialize JLanguageTool in background thread
        this.langTool = new JLanguageTool(new AmericanEnglish());

        // Pre-warm the language tool with a dummy check
        Task<Void> warmupTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    langTool.check("warmup"); // Dummy text to initialize
                } catch (IOException e) {
                    // Ignore warmup errors
                }
                return null;
            }
        };
        new Thread(warmupTask).start();
    }

    private void showSuggestions(Suggestion suggestion) {
        if (menu != null && menu.isShowing()) {
            menu.hide();
        }

        menu = new ContextMenu();
        menu.getStyleClass().add("context-menu");

        // Heading
        MenuItem heading = new MenuItem("Suggestions:");
        heading.setDisable(true);
        heading.getStyleClass().add("context-menu-heading");
        menu.getItems().add(heading);

        // Add suggestions (limit to 2)
        int limit = Math.min(2, suggestion.getReplacements().size());
        for (int i = 0; i < limit; i++) {
            String replacement = suggestion.getReplacements().get(i);
            MenuItem item = new MenuItem(replacement);
            item.setOnAction(e -> {
                textArea.replaceText(suggestion.getStartIndex(), suggestion.getEndIndex(), replacement);
            });
            menu.getItems().add(item);
        }

        // Show at caret position
        Bounds caretBounds = textArea.getCaretBounds().orElse(null);
        if (caretBounds != null) {
            menu.show(textArea, caretBounds.getMinX(), caretBounds.getMaxY());
        }
    }

    public void process(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        // Cancel previous timer
        if (debounceTimer != null) {
            debounceTimer.cancel();
        }

        // Debounce: wait 300ms after user stops typing
        debounceTimer = new Timer();
        debounceTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                processText(text);
            }
        }, 300); // 300ms delay
    }

    private void processText(String text) {
        // Run grammar check in background thread
        Task<List<Suggestion>> task = new Task<List<Suggestion>>() {
            @Override
            protected List<Suggestion> call() throws Exception {
                try {
                    return langTool.check(text).stream()
                            .map(match -> new SuggestionManager(match, text))
                            .collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                    return List.of();
                }
            }
        };

        task.setOnSucceeded(e -> {
            Platform.runLater(() -> {
                List<Suggestion> suggestions = task.getValue();

                // Clear all previous styling and set default white text
                textArea.clearStyle(0, text.length());
                if (text.length() > 0) {
                    textArea.setStyle(0, text.length(), "-fx-fill: white;");
                }

                // Apply error underlining
                for (Suggestion suggestion : suggestions) {
                    int start = suggestion.getStartIndex();
                    int end = suggestion.getEndIndex();

                    // Apply red underline while keeping white text
                    textArea.setStyle(start, end, "-fx-fill: white; -fx-underline: true; -rtfx-underline-color: red; -rtfx-underline-width: 2px;");
                }

                // Handle right-click for suggestions
                textArea.setOnMousePressed(event -> {
                    if (event.isSecondaryButtonDown()) {
                        int caretPos = textArea.getCaretPosition();
                        for (Suggestion s : suggestions) {
                            if (caretPos >= s.getStartIndex() && caretPos <= s.getEndIndex()) {
                                showSuggestions(s);
                                break;
                            }
                        }
                    }
                });
            });
        });

        new Thread(task).start();
    }
}