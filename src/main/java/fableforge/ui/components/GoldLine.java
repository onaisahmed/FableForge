package fableforge.ui.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GoldLine extends Line {

    public GoldLine(int startX, int startY, int endX, int endY) {
        this.setStartX(startX);
        this.setEndX(endX);
        this.setStartY(startY);
        this.setEndY(endY);
        this.setStroke(Color.GOLD);
        this.setStrokeWidth(1);
        this.setOpacity(0.2);
        this.setSmooth(true);
    }
}

