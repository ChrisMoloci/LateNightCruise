package com.gearshifgroove.late_night_cruise.CustomUIElements;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button {
    private Color baseColor;

    public CustomButton(String text, Font font, int width, int height, Color color, Color textColor) {
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

        // Hover effects

    }

    public void setBaseColor(Color newColor) {
        this.baseColor = newColor;
        setBackground(new Background(new BackgroundFill(
                newColor,
                new CornerRadii(16),
                Insets.EMPTY
        )));
    }

    private void setupHoverEffects() {
        setOnMouseEntered(e -> setBackground(new Background(
                new BackgroundFill(baseColor.deriveColor(0, 1, 1.2, 1),
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