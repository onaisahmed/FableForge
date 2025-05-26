package fableforge.ui.components;

import javafx.scene.control.TextArea;

public class StoryDisplayBox extends TextArea {

    public StoryDisplayBox() {
        this.setWrapText(true);
        this.setLayoutX(35);
        this.setLayoutY(250);
        this.setPrefWidth(525);
        this.setPrefHeight(250);
        this.setStyle(
                "-fx-control-inner-background:#050a10;" +
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: #e8e0c9;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-family: 'Georgia';" +
                        "-fx-border-color: #a67c52;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 15;" +
                        "-fx-background-insets: 0;"
        );
        // this.setEditable(false); // optionally make read-only
    }
}

