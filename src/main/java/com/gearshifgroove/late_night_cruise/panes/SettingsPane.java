package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;
import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.scenes.CreditScene;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.effect.DropShadow;

// Author(s): Christian Moloci, Tito Migabo

public class SettingsPane extends BorderPane {
    //Style constants for buttons
    private static final Font BUTTON_FONT = Font.font("Arial", 18);
    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 40;

    // UI components
    private CustomButton muteButton;
    private CustomButton backButton;
    private CustomButton creditsButton;

    public SettingsPane() {
        LateNightCruise.mainStage.setTitle("Settings");
        setupBackground(); //setting background to gradient
        setupUI(); //interface elements
        setupActions(); // button behaviors
    }

    //Applying gradient background
    private void setupBackground() {
        this.setStyle("-fx-background-color: linear-gradient(to bottom, #ff00ff, #0000ff, #001f3f);");
    }

    //Main interface layout
    private void setupUI() {
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(30));

        // Title
        Text title = new Text("SETTINGS");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.GOLD);
        title.setEffect(new DropShadow(10, Color.BLACK));

        // Buttons
        VBox buttonBox = new VBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        backButton = new CustomButton(
                "Main Menu",
                BUTTON_FONT,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                Color.LIGHTGRAY,
                Color.BLACK
        );

        muteButton = new CustomButton(
                GlobalPlayer.getAudioMuteState() ? "Unmute" : "Mute",
                BUTTON_FONT,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                GlobalPlayer.getAudioMuteState() ? Color.ORANGE : Color.RED,
                Color.WHITE
        );

        creditsButton = new CustomButton(
                "View Credits",
                BUTTON_FONT,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                Color.DODGERBLUE,
                Color.WHITE
        );

        //Adds components to layout
        buttonBox.getChildren().addAll(backButton, muteButton, creditsButton);
        mainContainer.getChildren().addAll(title, buttonBox);
        this.setCenter(mainContainer);
    }

    //Configuring button click
    private void setupActions() {
        backButton.setOnAction(e ->//returning to main menu
                LateNightCruise.mainStage.setScene(new MainMenuScene()));

        //audio mute state
        muteButton.setOnAction(e -> {
            boolean newMuteState = !GlobalPlayer.getAudioMuteState();
            GlobalPlayer.changeAudioMuteState(newMuteState);

            //Updated button text and color
            muteButton.setText(newMuteState ? "Unmute" : "Mute");
            muteButton.setBaseColor(newMuteState ? Color.ORANGE : Color.RED);
        });

        //Showing the credit screen
        creditsButton.setOnAction(e -> {
            LateNightCruise.mainStage.setScene(new CreditScene());
            LateNightCruise.mainStage.setFullScreen(true);
        });
    }
}