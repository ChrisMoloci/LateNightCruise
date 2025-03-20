package com.gearshifgroove.late_night_cruise.CustomUIElements;

// Author: Christian Moloci
// Student #: 0874409
// Date: 02/21/2025
// Class: MAD200 002

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button {
    public CustomButton(String text, Font font, int width, int height, Color color, Color textColor) {
        super(text);
        this.setFont(font);
        this.setMaxWidth(width);
        this.setMinHeight(height);
        this.setFont(font);
        this.setBackground(new Background(new BackgroundFill(color, new CornerRadii(16), Insets.EMPTY)));
    }
}
