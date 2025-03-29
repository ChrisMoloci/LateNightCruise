package com.gearshifgroove.late_night_cruise.CustomUIElements;

import javafx.scene.layout.VBox;

// Author(s): Christian Moloci

// A box that sets vertical and/or horizontal space between different nodes when used
public class PaddingBox extends VBox {
    // Takes in a width and height
    public PaddingBox(int height, int width) {
        // Set width and height based on values passed to constructor
        this.setMinHeight(height);
        this.setMinWidth(width);
    }
}
