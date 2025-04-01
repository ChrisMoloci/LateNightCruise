package com.gearshifgroove.late_night_cruise;

// Author(s): Ebrahim Jabir

import javafx.scene.image.Image;
// The Coin class represents a collectible coin in the game, inheriting from Tile.
public class Coin extends Tile{


    //// Constructor that calls the parent Tile class constructor to set position and image
    public Coin(int x, int y, Image image){
        // Calls the parent class (Tile) constructor to set position and image.
        super(x,y,0,0,image);
    }
}
