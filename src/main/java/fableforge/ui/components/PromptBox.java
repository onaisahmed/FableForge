package fableforge.ui.components;

import org.fxmisc.richtext.InlineCssTextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import fableforge.services.NlpService;

public class PromptBox extends InlineCssTextArea {

    private final NlpService nlpService;

    public PromptBox() {
//        this.setPromptText("Enter your story idea...");
        this.setPrefWidth(400);
        this.setPrefHeight(40); // Set explicit height to match TextField
        this.setMaxHeight(40);   // Prevent it from growing taller
        this.setLayoutX(35);
        this.setLayoutY(185);

        // Keep your original styling with white cursor
        this.setStyle(
                "-fx-background-color: #050a10;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: gray;" +
                        "-fx-border-color: #a67c52;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10;" +
                        "-fx-font-size: 14px;" +
                        "-fx-text-inner-color: white;" +
                        "-fx-highlight-fill: #a67c52;" +
                        "-fx-highlight-text-fill: white;"
        );

        // Set default paragraph style to ensure white text and cursor
        this.setStyle(0, 0, "-fx-fill: white;");

        // Add CSS for error underlining
        this.getStylesheets().add("data:text/css," +
                ".error-underline { " +
                "    -fx-text-fill: white; " +
                "    -fx-underline: true; " +
                "    -rtfx-underline-color: red; " +
                "    -rtfx-underline-dash-array: none; " +
                "    -rtfx-underline-width: 2px; " +
                "}"
        );

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(5);
        glow.setSpread(0.2);

        this.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                this.setEffect(glow);
            } else {
                this.setEffect(null);
            }
        });

        // Initialize NLP service
        this.nlpService = new NlpService(this);

        // Process text on change
        this.textProperty().addListener((obs, oldText, newText) -> {
            nlpService.process(newText);
        });
    }
}