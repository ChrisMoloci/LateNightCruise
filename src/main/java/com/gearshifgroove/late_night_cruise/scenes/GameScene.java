package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.GamePane;
import javafx.scene.Scene;

public class GameScene extends Scene {
    public GameScene() {
        super(new GamePane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
