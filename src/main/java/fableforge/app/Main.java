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
                System.out.println("Please enter a prompt.");
                return;
            }

            // Generate story using your core logic
            String story = StoreGenerator.generateStory(userInput);

            // Display the generated story in the StoryDisplayBox
            mainScreen.getStoryBox().appendText("Topic: " + userInput + "\n\n");
            mainScreen.getStoryBox().appendText("Story: " + story + "\n\n");

            // Clear the prompt input
            mainScreen.getPromptBox().clear();
        });

        Scene scene = new Scene(mainScreen.getRoot(), 600, 600); // Adjust size as needed
        primaryStage.setTitle("FableForge Story Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Starts JavaFX application
    }
}
