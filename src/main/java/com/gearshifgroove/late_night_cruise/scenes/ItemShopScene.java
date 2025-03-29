package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.ItemShopPane;
import javafx.scene.Scene;

// Author(s): Christian Moloci

// Sets the main stage to the ItemShopScene which displays an ItemShopPane
public class ItemShopScene extends Scene {

    public ItemShopScene(){
        super(new ItemShopPane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
