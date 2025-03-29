package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.panes.CreditsPane;
import javafx.scene.Scene;

// Author(s): Christian Moloci

// Sets the main stage to the settings scene which displays a settings pane
public class CreditScene extends Scene {
    public CreditScene() {
        super(new CreditsPane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        this.setOnKeyPressed(e -> {
            LateNightCruise.mainStage.setScene(new SettingsScene());
        });
    }
}
