package com.gearshifgroove.late_night_cruise;

import javafx.scene.image.Image;

// Author(s): Christian Moloci

// Player class for car object that extends tile
public class Player extends Tile {
    // contains a gas and coin count var
    private int gasCount;
    private int coinCount;

    // Main constructor that takes in teh necessary values of its super constructor
    public Player(int x, int y, Image image) {
        super(x, y, 0, 0, image);
    }

    // Getter and setter for gas level
    public int getGasCount() {
        return gasCount ;
    }

    public void setGasCount(int gasCount) {
        this.gasCount = gasCount;
    }

    // Getter and setter for coin count
    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    // Moves player to the left unless they reach the maximum left position
    public void moveLeft() {
        if (this.getxCoord() > 795) {
            this.setxCoord(this.getxCoord() - 88);
        } else {
            System.out.println("Can't move left");
        }
        System.out.println(this.getxCoord());
    }

    // Moves player to the right unless they reach the maximum right position
    public void moveRight() {
        if (this.getxCoord() < 1059) {
            this.setxCoord(this.getxCoord() + 88);
        } else {
            System.out.println("Can't move right");
        }
        System.out.println(this.getxCoord());
    }
}