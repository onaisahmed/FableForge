package fableforge.ui.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GoldLine extends Line {

    public GoldLine() {
        this.setStartX(35);
        this.setEndX(565);
        this.setStartY(165);
        this.setEndY(165);
        this.setStroke(Color.GOLD);
        this.setStrokeWidth(1.5);
        this.setOpacity(0.2);
        this.setSmooth(true);
    }
}

