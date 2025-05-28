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
                mainScreen.getStoryBox().clear();
                mainScreen.getStoryBox().appendText("A prompt, please. Every story needs a start!");
                return;
            }
            // Clearing previous story first [BUG fixed]
            mainScreen.getStoryBox().clear();
            // Generate story using your core logic
            String story = StoreGenerator.generateStory(userInput);

            // Display the generated story in the StoryDisplayBox
            mainScreen.getStoryBox().appendText(story);
        });

        Scene scene = new Scene(mainScreen.getRoot(), 600, 600);
        primaryStage.setTitle("FableForge Story Generator");
        // Disable resizing
        primaryStage.setResizable(false);

        primaryStage.setWidth(613);
        primaryStage.setHeight(637);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
