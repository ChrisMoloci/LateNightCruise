package com.gearshifgroove.late_night_cruise;

public class Tile {
    private int xCoord;
    private int yCoord;
    private int xCordSpeed;
    private int yCordSpeed;
    private String file;

    public Tile(int xCoord, int yCoord, int xCordSpeed, String file) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xCordSpeed = xCordSpeed;
        this.file = file;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
