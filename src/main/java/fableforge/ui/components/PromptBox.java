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
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: gray;" +
                        "-fx-border-color: gold;" +
                        "-fx-border-radius: 5;" +
                        "-fx-padding: 10;" +
                        "-fx-font-size: 14px;"
        );

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(10);
        glow.setSpread(0.5);

        this.setOnMouseEntered(e -> this.setEffect(glow));
        this.setOnMouseExited(e -> this.setEffect(null));
    }
}

