package com.gearshifgroove.late_night_cruise;

import javafx.scene.image.Image;
// Author(s): Ebrahim Jabir

// The Fuel class represents a fuel object in the game, inheriting from Tile.
public class Fuel extends Tile{

    // Constructor to initialize a fuel object with position (x, y) and an image.
    public Fuel(int x, int y, Image image){
        // Calls the parent class (Tile) constructor to set position and image.
        super(x,y,0,0,image);
    }

}
