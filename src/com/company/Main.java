package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you like to run a Console or JPanel UI (C/J): ");
        String choice = input.next();
        if(choice.equalsIgnoreCase("c")) {
            RunConsoleApp();
        }
        else {
            RunUIApp();
        }
    }

    private static void RunUIApp() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyFrame frame = new MyFrame();
                frame.setTitle("Conway's Game of Life Grid");
                frame.setSize(800, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                frame.InitialSetup();
                frame.setVisible(true);
            }
        });
    }

    private static void RunConsoleApp() {
        System.out.println("Setup grid size by defining it's x and y position...");
//        System.out.println("Enter X-Position: ");
//        String xPos = input.next();
//        System.out.println("Enter Y-Position: ");
//        String yPos = input.next();
//        int x = Integer.parseInt(xPos);
//        int y = Integer.parseInt(yPos);

        // Setup myGrid size and initialize
        Grid myGrid = new Grid(5,5);

        // Setup certain cells alive as initiate the game
        myGrid.UpdateGrid(2,2, true);
        myGrid.UpdateGrid(2,3, true);
        myGrid.UpdateGrid(2,4, true);

        myGrid.PrintGrid();

        myGrid.Play();

        myGrid.Play();

        myGrid.Play();

        myGrid.Play();

        myGrid.Play();


        System.out.println("-------------------------------");
        System.out.println("Print neighbors of a given cell [2,2]:");
        ArrayList<Cell> cells = myGrid.CurrentNeighbours(2,2);
        System.out.println("Print neighbors of a given cell [2,2]: Count=>" + cells.size());
        for (Cell c:cells) {
            System.out.println(c.toString());
        }
    }
}
