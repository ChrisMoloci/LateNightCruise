package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.SettingsPane;
import javafx.scene.Scene;

public class SettingsScene extends Scene {
    public SettingsScene() {
        super(new SettingsPane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
