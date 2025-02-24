package com.gearshifgroove.late_night_cruise;

import javafx.scene.image.Image;

public class Player extends Tile {
    private int gasLevel;
    private int coinCount;

    public Player(int x, int y, Image image) {
        super(x, y, 0, 0, image);
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public void setGasLevel(int gasLevel) {
        this.gasLevel = gasLevel;
    }

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
