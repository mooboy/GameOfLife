package com.company;

import java.util.ArrayList;

public class Grid {
    int column;
    int row;
    Cell[][] board;

    // Constructor for a grid
    // x and y value setup the 2 dimensional array size
    public Grid(int r, int c) {
        this.column = c;
        this.row = r;
        this.board = new Cell[r][c];
    }

    // Print the current state of the grid
    // loop through every cell and print . for dead and * for alive
    public void PrintGrid() {
        for (int i = 0; i < this.row; i++) {
            String boardGraph = "|";
            for (int j = 0; j < this.column; j++) {
                if(this.board[i][j] == null || !this.board[i][j].getState()) {
                    boardGraph += ".";
                }
                else {
                    boardGraph += "*";
                }
            }

            boardGraph += "|";
            System.out.println(boardGraph);
        }

        System.out.println();
    }

    // Update the given cell in a grid
    // boolean alive parameter determines the cells alive or dead status
    // When True then set cell value to 1; otherwise 0
    public void UpdateGrid(int x, int y, Boolean alive){
        if(x < 0 || x >= this.row){
            return;
        }

        if(y < 0 || y >= this.column) {
            return;
        }

        if(alive)
            this.board[x][y] = new Cell(x, y, true);
        else
            this.board[x][y] = new Cell(x, y, false);
    }

    public int CountAliveNeighbours(int x, int y) {
        return CurrentNeighbours(x, y).size();
//        int count = 0;
//        count  += getState((x-1),(y-1));
//        count  += getState(x,y-1);
//        count  += getState(x+1,y-1);
//
//        count  += getState(x-1,y);
//        count  += getState(x+1,y);
//
//        count  += getState((x-1),(y+1));
//        count  += getState(x+1,y+1);
//        count  += getState(x,y+1);
//
//        return count;
    }


    public ArrayList<Cell> CurrentNeighbours(int x, int y) {
        ArrayList<Cell> cells = new ArrayList<Cell>();

        if (getState((x-1),(y-1))==1)
            cells.add(this.board[x-1][y-1]);
        if (getState((x),(y-1))==1)
            cells.add(this.board[x][y-1]);
        if (getState((x+1),(y-1))==1)
            cells.add(this.board[x+1][y-1]);
        if (getState((x-1),(y))==1)
            cells.add(this.board[x-1][y]);
        if (getState((x+1),(y))==1)
            cells.add(this.board[x+1][y]);
        if (getState((x-1),(y+1))==1)
            cells.add(this.board[x-1][y+1]);
        if (getState((x+1),(y+1))==1)
            cells.add(this.board[x+1][y+1]);
        if (getState((x),(y+1))==1)
            cells.add(this.board[x][y+1]);

        return cells;
    }

    // Get the value of given cell
    // if the index x or y, is out of bound, this will return 0
    public int getState(int x, int y) {
        if(x < 0 || x>= this.row){
            return 0;
        }

        if(y < 0 || y >= this.column) {
            return 0;
        }

        if(this.board[x][y] != null && this.board[x][y].getState()) {
            return 1;
        }

        return 0;
    }

    public void Play() {

        // Create a new board
        Cell[][] newBoard = new Cell[this.row][this.column];

        // Compute new STATE based on current board
        for(int x = 0; x < this.row; x++) {
            for (int y = 0; y < this.column; y++) {

                int aliveNeighbours = this.CountAliveNeighbours(x, y);
                int currentState = this.getState(x, y);
                if(currentState == 0 && aliveNeighbours == 3) {
                    //  Any dead cell with exactly three live neighbours becomes a live cell,
                    //  as if by reproduction.
                    newBoard[x][y] = new Cell(x, y, true);
                }
                else if(currentState == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                    // Any live cell, with fewer than two live neighbours or greater than 3 live neighbours,
                    // dies due to over or under population.
                    newBoard[x][y] = new Cell(x, y, false);
                }
                else {
                    // Any live cell with two or three live neighbours lives on to the next generation
                    // that means keeping its current status without change
                    newBoard[x][y] = new Cell(x, y, currentState == 1);
                }
            }
        }

        this.board = newBoard;
        PrintGrid();
    }
}
