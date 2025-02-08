package com.gearshifgroove.late_night_cruise;

import javafx.scene.image.Image;

public class Tile {
    private int xCoord;
    private int yCoord;
    private int xCordSpeed;
    private int yCordSpeed;
    private Image image;

    public Tile(int xCoord, int yCoord, int xCordSpeed, int yCordSpeed, Image image) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xCordSpeed = xCordSpeed;
        this.yCordSpeed = yCordSpeed;
        this.image = image;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getxCordSpeed() {
        return xCordSpeed;
    }

    public void setxCordSpeed(int xCordSpeed) {
        this.xCordSpeed = xCordSpeed;
    }

    public int getyCordSpeed() {
        return yCordSpeed;
    }

    public void setyCordSpeed(int yCordSpeed) {
        this.yCordSpeed = yCordSpeed;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image file) {
        this.image = image;
    }
}
