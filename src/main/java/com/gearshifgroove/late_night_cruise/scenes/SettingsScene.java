package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.SettingsPane;
import javafx.scene.Scene;

// Author(s): Christian Moloci

// Sets the main stage to the SettingsScene which displays a SettingsPane
public class SettingsScene extends Scene {
    public SettingsScene() {
        super(new SettingsPane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
