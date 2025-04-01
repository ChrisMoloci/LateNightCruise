package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.CreditCluster;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

// Author(s): Christian Moloci, Tito Migabo

public class CreditsPane extends BorderPane {
    public CreditsPane() {
        // Set the title of the app to display Credits
        LateNightCruise.mainStage.setTitle("Credits");

        // Style the Background
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // VBox to store all credits and give the scrolling effect to
        VBox allCredits = new VBox();
        allCredits.setMaxWidth(100);
        allCredits.setSpacing(1);

        // Game name at the top of the credits
        Text title = new Text("Late Night Cruise");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);

        // Lead Developers
        CreditCluster leadDevs = new CreditCluster(
                new Text("Lead Developers"),
                new ArrayList<Text>(Arrays.asList(
                        new Text("Christian Moloci"),
                        new Text("Ebrahim Jabir Omer"),
                        new Text("Tito Migabo")
                ))
        );

        // Development Resources
        CreditCluster devResources = new CreditCluster(
                new Text("Development Resources"),
                new ArrayList<Text>(Arrays.asList(
                        new Text("Java - Oracle"),
                        new Text("JavaFX - Oracle")
                ))
        );

        // Menu Devs
        CreditCluster menuDevelopers = new CreditCluster(
                new Text("Menu Developers"),
                new ArrayList<Text>(Arrays.asList(
                        new Text("Tito Migabo")
                ))
        );

        // Main game devs
        CreditCluster mainGameDevelopers = new CreditCluster(
                new Text("Main Game Developers"),
                new ArrayList<Text>(Arrays.asList(
                        new Text("Ebrahim Jabir Omer"),
                        new Text("Christian Moloci")
                ))
        );

        // Musical backend devs
        CreditCluster musicalBackendDevelopers = new CreditCluster(
                new Text("Musical Backend Developers"),
                new ArrayList<Text>(Arrays.asList(
                        new Text("Christian Moloci")
                ))
        );

        // Music store devs
        CreditCluster musicStoreDevelopers = new CreditCluster(
                new Text("Music Store Developers"),
                new ArrayList<Text>(Arrays.asList(
                        new Text("Christian Moloci")
                ))
        );

        CreditCluster assetSources = new CreditCluster(
            new Text("Asset Designs"),
            new ArrayList<Text>(Arrays.asList(
                    new Text("Grass Texture - Proxy Games (CC0)"),
                    new Text("Car Texture - awesomeduck (CC0)"),
                    new Text("Road Texture - Alucard (CC-BY 3.0)"),
                    new Text("Lamp Post Texture - matepore (CC0)"),
                    new Text("Tree Texture - overcrafted (OGA-BY 3.0)"),
                    new Text("Nature Tile Collection - g4mersylver (CC0)"),
                    new Text("Coin Texture - kotnaszynce (CC0)"),
//                    new Text("Engine Sound - DerMeehdrescher (CC-BY-SA 4.0)")
                    new Text("Gas Can - adnanroesdi (vecteezy free license)"),
                    new Text("Play/Pause Icons - Free Pic")
            ))
        );

        CreditCluster music = new CreditCluster(
                new Text("Music"),
                new ArrayList<Text>(Arrays.asList(
                    new Text("Music credited in store")
                ))
        );

        // group name at the top right of the screen
        Text groupName = new Text("Gear Shift Groove");
        groupName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        groupName.setFill(Color.WHITE);

        // Add credit clusters to the allCredits VBox and set some styling
        allCredits.getChildren().addAll(title, new PaddingBox(30, 0),
                leadDevs, devResources, menuDevelopers, mainGameDevelopers,
                musicalBackendDevelopers, musicStoreDevelopers, assetSources,
                music);
        allCredits.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(allCredits, Pos.CENTER);
        BorderPane.setAlignment(groupName, Pos.TOP_RIGHT);

        // Add credits and group name to the screen
        this.setCenter(allCredits);
        this.setTop(groupName);

        // Fade the BorderPane in
        FadeTransition intro = new FadeTransition(Duration.millis(1000), this);
        intro.setFromValue(0);
        intro.setToValue(1);
        intro.setCycleCount(1);
        intro.play();

        // Zoom in and out on title
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), title);
        scaleTransition.setByX(0.2);
        scaleTransition.setByY(0.2);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.setAutoReverse(true);

        // Moves the credits up
        TranslateTransition creditScroll = new TranslateTransition(Duration.seconds(20), allCredits);
        creditScroll.setFromY(Const.WINDOW_HEIGHT);
        creditScroll.setToY(Const.WINDOW_WIDTH * -2.1);
        creditScroll.setCycleCount(1);

        // Plays both the zoom and scroll transition at the same time
        ParallelTransition creditTransitions = new ParallelTransition();
        creditTransitions.getChildren().setAll(scaleTransition, creditScroll);
        creditTransitions.play();

        // Changes the fill color on the groupNameText
        FillTransition groupNameFillTransition = new FillTransition(Duration.seconds(2), groupName);
        groupNameFillTransition.setFromValue(Color.GREEN);
        groupNameFillTransition.setToValue(Color.ORANGERED);
        groupNameFillTransition.setCycleCount(Timeline.INDEFINITE);
        groupNameFillTransition.setAutoReverse(true);

        // Rotates the group name text
        RotateTransition groupNameRotateTransition = new RotateTransition(Duration.seconds(1), groupName);
        groupNameRotateTransition.setByAngle(360);
        groupNameRotateTransition.setCycleCount(1);
        groupNameFillTransition.setInterpolator(Interpolator.LINEAR);

        // Wait transition that does nothing for 1 second
        TranslateTransition wait = new TranslateTransition(Duration.seconds(1), groupName);
        wait.setCycleCount(1);

        // Plays the rotate and wait transition in a sequential order
        SequentialTransition groupNameSequence = new SequentialTransition();
        groupNameSequence.getChildren().addAll(groupNameRotateTransition, wait);
        groupNameSequence.setCycleCount(Timeline.INDEFINITE);

        // Plays the sequential group name transition and the fill transition at the same time
        ParallelTransition groupNameParallelTransition = new ParallelTransition();
        groupNameParallelTransition.getChildren().addAll(groupNameFillTransition, groupNameSequence);
        groupNameParallelTransition.setCycleCount(Timeline.INDEFINITE);
        groupNameParallelTransition.play();

    }
}