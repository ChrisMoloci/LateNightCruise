package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SettingsPane extends VBox {
    Button muteButton = new Button();
    Button backButton = new Button("Main Menu");
    public SettingsPane() {
        backButton.setOnAction(e->{
            LateNightCruise.mainStage.setScene(new MainMenuScene());
        });

        muteButton.setText(GlobalPlayer.getAudioMuteState() ? "Unmute" : "mute");
        muteButton.setOnAction(e -> {
            boolean muteState = GlobalPlayer.getAudioMuteState();
            if (muteState) {
                GlobalPlayer.changeAudioMuteState(false);
            } else {
                GlobalPlayer.changeAudioMuteState(true);
            }
            muteButton.setText(GlobalPlayer.getAudioMuteState() ? "Unmute" : "Mute");
        });
        this.getChildren().addAll(backButton, muteButton);
    }
}
