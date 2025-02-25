package com.gearshifgroove.late_night_cruise;

import javafx.scene.image.Image;

public class Coin extends Tile{


    //// Constructor that calls the parent Tile class constructor to set position and image
    public Coin(int x, int y, Image image){
        super(x,y,0,0,image);
    }
}
