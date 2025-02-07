package com.gearshifgroove.late_night_cruise;

public class Player extends Tile {
    private int gasLevel;
    private int coinCount;

    public Player(int x, int y) {
        super(x, y, 0);
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
