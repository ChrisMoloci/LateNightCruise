package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;
import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.panes.CreditsPane;
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

public class SettingsPane extends BorderPane {
    private static final Font BUTTON_FONT = Font.font("Arial", 18);
    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 40;

    private CustomButton muteButton;
    private CustomButton backButton;
    private CustomButton creditsButton;

    public SettingsPane() {
        setupBackground();
        setupUI();
        setupActions();
    }

    private void setupBackground() {
        this.setStyle("-fx-background-color: linear-gradient(to bottom, #ff00ff, #0000ff, #001f3f);");
    }

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
                Color.WHITE
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

        buttonBox.getChildren().addAll(backButton, muteButton, creditsButton);
        mainContainer.getChildren().addAll(title, buttonBox);
        this.setCenter(mainContainer);
    }

    private void setupActions() {
        backButton.setOnAction(e ->
                LateNightCruise.mainStage.setScene(new MainMenuScene()));

        muteButton.setOnAction(e -> {
            boolean newMuteState = !GlobalPlayer.getAudioMuteState();
            GlobalPlayer.changeAudioMuteState(newMuteState);

            muteButton.setText(newMuteState ? "Unmute" : "Mute");
            muteButton.setBaseColor(newMuteState ? Color.ORANGE : Color.RED);
        });

        creditsButton.setOnAction(e -> {
            CreditsPane credits = new CreditsPane();
            Scene creditsScene = new Scene(credits, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
            LateNightCruise.mainStage.setScene(creditsScene);
        });
    }
}