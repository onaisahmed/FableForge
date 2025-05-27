package fableforge.app;

import fableforge.core.StoreGenerator;
import fableforge.ui.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainScreen mainScreen = new MainScreen();

        // Set up the button action to generate and display the story
        mainScreen.getButton().setOnAction(e -> {
            String userInput = mainScreen.getPromptBox().getText();
            if (userInput.trim().isEmpty()) {
                mainScreen.getStoryBox().appendText("Please enter a prompt to generate Story!");
                return;
            }
            // Clearing previous story first [BUG fixed]
            mainScreen.getStoryBox().clear();

            // Generate story using your core logic
            String story = StoreGenerator.generateStory(userInput);

            // Display the generated story in the StoryDisplayBox
            mainScreen.getStoryBox().appendText(story);

            // Clear the prompt input
//            mainScreen.getPromptBox().clear();
        });

        Scene scene = new Scene(mainScreen.getRoot(), 600, 600); // Adjust size as needed
        primaryStage.setTitle("FableForge Story Generator");
        // Disable resizing
        primaryStage.setResizable(false);

        // Optional: set fixed size (if needed)
        primaryStage.setWidth(613);
        primaryStage.setHeight(637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Starts JavaFX application
    }
}
