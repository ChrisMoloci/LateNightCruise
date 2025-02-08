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
}
