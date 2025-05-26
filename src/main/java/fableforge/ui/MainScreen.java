package fableforge.ui;

import fableforge.ui.components.*;
import javafx.scene.layout.Pane;

public class MainScreen {

    public BackgroundPane root;
    public GoldLine line;
    public PromptBox promptBox;
    public StoryDisplayBox storyBox;
    public CustomButton button;

    public MainScreen() {

        root = new BackgroundPane();

        line = new GoldLine();
        root.getChildren().add(line);

        promptBox = new PromptBox();
        root.getChildren().add(promptBox);

        storyBox = new StoryDisplayBox();
        root.getChildren().add(storyBox);

        button = new CustomButton();
        root.getChildren().add(button.getButton());

        // Define button action
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
