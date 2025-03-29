package com.gearshifgroove.late_night_cruise;

import javafx.scene.image.Image;

// Author(s): Christian Moloci

// Tile is a game asset element that contains the essential vars and functions and can either be used or extended in other classes
public class Tile {
    // Setting the vars
    private int xCoord;
    private int yCoord;
    private int xCordSpeed;
    private int yCordSpeed;
    private Image image;

    // Main constructor with the necessary vars to instantiate
    public Tile(int xCoord, int yCoord, int xCordSpeed, int yCordSpeed, Image image) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xCordSpeed = xCordSpeed;
        this.yCordSpeed = yCordSpeed;
        this.image = image;
    }

    // Getter and setter for x coord
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    // Getter and setter for y coord
    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    // Getter and setter for x coord speed
    public int getxCordSpeed() {
        return xCordSpeed;
    }

    public void setxCordSpeed(int xCordSpeed) {
        this.xCordSpeed = xCordSpeed;
    }

    // Getter and setter for y coord speed
    public int getyCordSpeed() {
        return yCordSpeed;
    }

    public void setyCordSpeed(int yCordSpeed) {
        this.yCordSpeed = yCordSpeed;
    }

    // Getter and setter for the tile image
    public Image getImage() {
        return image;
    }

    public void setImage(Image file) {
        this.image = image;
    }
}
