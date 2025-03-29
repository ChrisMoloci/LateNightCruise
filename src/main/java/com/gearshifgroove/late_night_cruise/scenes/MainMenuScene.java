package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.MainMenuPane;
import javafx.scene.Scene;

// Author(s): Christian Moloci

// Sets the main stage to the MainMenuScene which displays a MainMenuPane
public class MainMenuScene extends Scene {
    public MainMenuScene() {
        super(new MainMenuPane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
