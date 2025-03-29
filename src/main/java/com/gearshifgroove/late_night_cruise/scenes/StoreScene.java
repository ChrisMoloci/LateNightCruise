package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.scene.Scene;

// Author(s): Christian Moloci

// Sets the main stage to the StoreScene which displays a StoreScene
public class StoreScene extends Scene {
    public StoreScene() {
        super(new StorePane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
