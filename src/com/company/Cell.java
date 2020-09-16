package com.company;

// Cell class determines the state of each cell on the grid (alive/dead) as well as the position on the grid
public class Cell {
    Boolean state;
    int xPosition;
    int yPosition;


    public Cell() {

    }

    // Cell constructor takes x, y, and state of cell
    public Cell(int x, int y, boolean isAlive) {
        this.xPosition = x;
        this.yPosition = y;
        this.state = isAlive;
    }

    // method returns the state of the cell
    public Boolean getState() {
        return this.state;
    }

    // method returns state and position of cell
    public String toString() {
        return "IsAlive: " + this.state + " | " + "X-Pos: " + this.xPosition + "  |  Y-Pos:" + this.yPosition;
    }
}
