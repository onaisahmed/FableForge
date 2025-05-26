package fableforge.ui;

import fableforge.ui.components.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MainScreen {

    public BackgroundPane root;
    public GoldLine line;
    public GoldLine line2;
    public PromptBox promptBox;
    public StoryDisplayBox storyBox;
    public CustomButton button;

    public MainScreen() {
        root = new BackgroundPane();

        // Top decorative line
        line = new GoldLine(35, 165, 565, 165);
        root.getChildren().add(line);

        // Red container background
        Region redBox = new Region();
        redBox.setStyle("-fx-background-color: #060b12; -fx-background-radius: 5; " +
                "-fx-border-color: #a67c52; -fx-border-width: 0.5; -fx-border-radius: 10;");
        redBox.setLayoutX(33);
        redBox.setLayoutY(175);
        redBox.setPrefWidth(532);
        redBox.setPrefHeight(360);
        root.getChildren().add(0, redBox); // Add first so it stays behind

        // VBox to hold the components with spacing and padding
        VBox container = new VBox(15); // spacing between components
        container.setPadding(new Insets(15, 20, 20, 20)); // padding inside redBox
        container.setLayoutX(33);
        container.setLayoutY(175);
        container.setPrefWidth(532);
        container.setPrefHeight(340);

        promptBox = new PromptBox();
        line2 = new GoldLine(0, 0, 485, 0); // Will be adjusted inside VBox
        button = new CustomButton();
        storyBox = new StoryDisplayBox();

        // Add components to the VBox
        HBox inputRow = new HBox(10); // spacing between prompt and button
        inputRow.getChildren().addAll(promptBox, button.getButton());
        inputRow.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().addAll(inputRow, line2, storyBox);


        // Add VBox to root
        root.getChildren().add(container);

        // Button Action
        button.setOnAction(e -> {
            String userInput = promptBox.getText();
            if (userInput.trim().isEmpty()) {
                System.out.println("Please enter a prompt.");
                return;
            }
            System.out.println("User input: " + userInput);
            storyBox.appendText(userInput + "\n");
            promptBox.clear();
        });
    }

    public Pane getRoot() {
        return root;
    }

    public PromptBox getPromptBox() {
        return promptBox;
    }

    public StoryDisplayBox getStoryBox() {
        return storyBox;
    }

    public CustomButton getButton() {
        return button;
    }
}
