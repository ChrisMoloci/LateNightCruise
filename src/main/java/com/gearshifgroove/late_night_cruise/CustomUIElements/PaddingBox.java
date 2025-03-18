package com.gearshifgroove.late_night_cruise.CustomUIElements;

import javafx.scene.layout.VBox;

public class PaddingBox extends VBox {
    public PaddingBox(int height, int width) {
        this.setMinHeight(height);
        this.setMinWidth(width);
    }
}
