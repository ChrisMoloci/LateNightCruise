package com.gearshifgroove.late_night_cruise.CustomUIElements;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Author(s): Christian Moloci

// Create a custom button node used in some of the menus
public class CustomButton extends Button {
    // Set a base color
    private Color baseColor;

    // Get value for super constructor and also values for custom button
    public CustomButton(String text, Font font, int width, int height, Color color, Color textColor) {
        // Run the super constructor
        super(text);
        this.baseColor = color;

        // Basic styling
        setFont(font);
        setTextFill(textColor);
        setPrefSize(width, height);
        setBackground(new Background(new BackgroundFill(
                color,
                new CornerRadii(16),
                Insets.EMPTY
        )));

        // Unused hover effect
        this.setupHoverEffects();
    }

    // Sets the base color of the button
    public void setBaseColor(Color newColor) {
        this.baseColor = newColor;
        setBackground(new Background(new BackgroundFill(
                newColor,
                new CornerRadii(16),
                Insets.EMPTY
        )));
    }

    // When the button is hovered on, change the style a bit
    private void setupHoverEffects() {
        setOnMouseEntered(e -> setBackground(new Background(
                new BackgroundFill(baseColor.deriveColor(5, 5, 5, .9),
                        new CornerRadii(16),
                        Insets.EMPTY)
        )));

        setOnMouseExited(e -> setBackground(new Background(
                new BackgroundFill(baseColor,
                        new CornerRadii(16),
                        Insets.EMPTY)
        )));
    }
}