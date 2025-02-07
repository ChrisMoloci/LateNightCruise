package com.gearshifgroove.late_night_cruise;

public class Terrain {
    private int xCoord;
    private int yCoord;
    private int xCordSpeed;
    private int yCordSpeed;

    public Terrain(int xCoord, int yCoord, int xCordSpeed) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xCordSpeed = xCordSpeed;
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
}
