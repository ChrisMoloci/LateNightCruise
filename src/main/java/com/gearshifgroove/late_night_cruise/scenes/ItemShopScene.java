package com.gearshifgroove.late_night_cruise.scenes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.ItemShopPane;
import javafx.scene.Scene;

public class ItemShopScene extends Scene {

    public ItemShopScene(){
        super(new ItemShopPane(), Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
    }
}
