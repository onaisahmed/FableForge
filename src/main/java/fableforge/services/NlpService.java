package fableforge.services;

import fableforge.core.SuggestionManager;
import fableforge.core.model.Suggestion;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NlpService {
    private ContextMenu menu;

    private final JLanguageTool langTool;
    private final StyleClassedTextArea textArea;

    public NlpService(StyleClassedTextArea textArea) {
        this.textArea = textArea;
        textArea.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (menu != null && menu.isShowing()) {
                        menu.hide();
                    }
                });
            }
        });
        this.langTool = new JLanguageTool(new AmericanEnglish());
    }

    private void showSuggestions(Suggestion suggestion) {
        if (menu != null && menu.isShowing()) {
            menu.hide();
        }

        menu = new ContextMenu();
        menu.getStyleClass().add("context-menu");

        // Heading (non-clickable)
        MenuItem heading = new MenuItem("Suggestions:");
        heading.setDisable(true);
        heading.getStyleClass().add("context-menu-heading");
        menu.getItems().add(heading);

        // Add up to 2 suggestions
        int limit = Math.min(2, suggestion.getReplacements().size());
        for (int i = 0; i < limit; i++) {
            String replacement = suggestion.getReplacements().get(i);
            MenuItem item = new MenuItem(replacement);
            item.setOnAction(e -> textArea.replaceText(suggestion.getStartIndex(), suggestion.getEndIndex(), replacement));
            menu.getItems().add(item);
        }

        // Show at caret
        Bounds caretBounds = textArea.getCaretBounds().orElse(null);
        if (caretBounds != null) {
            menu.show(textArea, caretBounds.getMinX(), caretBounds.getMaxY());
        }
    }

    public void process(String text) {
        Platform.runLater(() -> {
            try {
                List<Suggestion> suggestions = langTool.check(text).stream()
                        .map(match -> new SuggestionManager(match, text))
                        .collect(Collectors.toList());

                textArea.clearStyle(0, text.length());

                for (Suggestion suggestion : suggestions) {
                    int start = suggestion.getStartIndex();
                    int end = suggestion.getEndIndex();

                    textArea.setStyleClass(start, end, "underline-red");

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
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
