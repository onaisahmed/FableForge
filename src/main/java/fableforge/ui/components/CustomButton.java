package fableforge.ui.components;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CustomButton {

    private Button button;
    private DropShadow hoverGlow;

    public CustomButton() {
        ImageView iview = new ImageView(new Image("Generate Button.png"));
        iview.setFitHeight(45);
        iview.setFitWidth(120);

        button = new Button("", iview);
        button.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-cursor: hand;"
        );

        hoverGlow = new DropShadow();
        hoverGlow.setColor(Color.GOLD);
        hoverGlow.setRadius(6);
        hoverGlow.setSpread(0.5);

        button.setOnMouseEntered(e -> button.setEffect(hoverGlow));
        button.setOnMouseExited(e -> button.setEffect(null));

        button.setLayoutX(435);
        button.setLayoutY(178);
    }

    public Button getButton() {
        return button;
    }

    public void setOnAction(javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        button.setOnAction(handler);
    }
}
