package com.gearshifgroove.late_night_cruise.panes;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CreditsPane extends StackPane {
    public CreditsPane() {
        setStyle("-fx-background-color: black;");

        Text credits = new Text(
                "Project: Late Night Cruise\n\n" +
                        "Developed By:\nTito Migabo\nEbrahim Jabir Omer\nChristian Moloci\n\n" +
                        "Art Assets: CC/Public Domain\nSound Effects: Freesound.org\n" +
                        "Music: Incompetech.com\nEngine: JavaFX"
        );

        credits.setFill(Color.WHITE);
        credits.setFont(Font.font("Arial", 24));
        getChildren().add(credits);

        setupAnimations(credits);
    }

    private void setupAnimations(Text credits) {
        // Initial position below screen
        credits.setTranslateY(400);

        // Animations
        TranslateTransition moveUp = new TranslateTransition(Duration.seconds(15), credits);
        moveUp.setToY(-800);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), credits);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), credits);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(12));

        RotateTransition rotate = new RotateTransition(Duration.seconds(15), credits);
        rotate.setByAngle(360);

        // Sequence setup
        SequentialTransition sequence = new SequentialTransition(
                new ParallelTransition(fadeIn, moveUp, rotate),
                fadeOut
        );

        sequence.setCycleCount(1);
        sequence.play();
    }
}