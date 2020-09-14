package com.company;

public class Cell {
    Boolean state;
    int xPosition;
    int yPosition;

    public Cell() {

    }

    public Cell(int x, int y, boolean isAlive) {
        this.xPosition = x;
        this.yPosition = y;
        this.state = isAlive;
    }

    public Boolean getState() {
        return this.state;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    public String toString() {
        return "IsAlive: " + this.state + " | " + "X-Pos: " + this.xPosition + "  |  Y-Pos:" + this.yPosition;
    }
}
