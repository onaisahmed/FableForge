package fableforge.ui.components;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class PromptBox extends TextField {

    public PromptBox() {
        this.setPromptText("Enter your story idea...");
        this.setPrefWidth(400);
        this.setLayoutX(35);
        this.setLayoutY(185);

        this.setStyle(
                "-fx-background-color: #050a10;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: gray;" +
                        "-fx-border-color: #a67c52;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 10;" +
                        "-fx-font-size: 14px;"
        );


        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(5);
        glow.setSpread(0.2);

        this.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                this.setEffect(glow); // Apply glow on focus
            } else {
                this.setEffect(null); // Remove glow when unfocused
            }
        });
    }
}

