package fableforge.ui.components;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppScene {

    public Scene scene;

    public AppScene(BackgroundPane root) {
        scene = new Scene(root, 600, 600);
        scene.setCursor(Cursor.HAND);
    }

    public Scene getScene() {
        return scene;
    }

    public static void setTitle(Stage stage, String title) {
        stage.setTitle(title);
    }

    public static void setFullScreen(Stage stage, boolean fullScreen) {
        stage.setFullScreen(fullScreen);
    }
}

