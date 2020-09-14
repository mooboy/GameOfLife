package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MyFrame extends JFrame {
    int count = 0;
    int xPos = 0;
    int yPos = 0;
    JPanel basePanel;
    JComboBox cbX;
    JComboBox cbY;
    JButton btnSetup;
    JPanel panel;
    JButton btnPlay;
    Grid myGrid;

    public void InitialSetup() {
        cbX = new JComboBox();
        cbY = new JComboBox();
        panel = new JPanel();
        btnSetup = new JButton("Setup size of the grid");
        btnPlay = new JButton("Play");
        btnPlay.setEnabled(false);

        panel.setPreferredSize(new Dimension(750, 650));
        panel.setBackground(Color.lightGray);

        for (int i = 1; i < 25; i++) {
            this.cbX.addItem(i);
            this.cbY.addItem(i);
        }

        this.setLayout(new FlowLayout());
        this.add(cbX);
        this.add(cbY);
        this.add(btnSetup);
        this.add(new JLabel("Click the cell to set initial state of the cell (alive/dead)"));
        this.add(panel);
        this.add(btnPlay);

        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayGame();
            }
        });

        btnSetup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetupPanelGrid();
            }
        });
    }


    public void SetupPanelGrid() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                xPos = Integer.parseInt(cbX.getSelectedItem().toString());
                yPos = Integer.parseInt(cbY.getSelectedItem().toString());
                count = 0;
                panel.removeAll();
                panel.setLayout(new GridLayout(xPos, yPos, 5, 5));
                System.out.println("Setting up new game...");

                for (int i = 0; i < xPos; i++) {
                    for (int j = 0; j < yPos; j++) {
                        JButton btnCell = new JButton("");
                        btnCell.setName(i + ":" + j);
                        btnCell.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                UpdateGrid(e);
                            }
                        });

                        panel.add(btnCell);
                    }
                }

                btnPlay.setEnabled(true);
                panel.revalidate();
                panel.repaint();
            }
        });
    }

    private void UpdateGrid(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn != null) {
            if(btn.getText().equalsIgnoreCase("")) {
                btn.setText("Alive");
                btn.setBackground(Color.darkGray);
            }
            else {
                btn.setText("");
                btn.setBackground(new JButton().getBackground());
            }
        }
    }

    private void PlayGame() {
        if(this.count == 0) {
            this.myGrid = new Grid(xPos, yPos);
        }

        UpdateGridStatusBasedOnUISelection();

        if(this.count == 0) {
            System.out.println("Initial state:");
            this.myGrid.PrintGrid();
        }

        this.myGrid.Play();
        UpdateUIButtonsBasedOnGrid();

        this.count++;
    }

    private void UpdateUIButtonsBasedOnGrid() {
        for (Component component : panel.getComponents()) {
            if (component.getClass().equals(JButton.class)) {
                JButton btn = (JButton)component;
                String[] cellXY = component.getName().split(":");
                int x = Integer.parseInt(cellXY[0]);
                int y = Integer.parseInt(cellXY[1]);
                if(this.myGrid.getState(x, y) == 1) {
                    btn.setText("Alive");
                    btn.setBackground(Color.darkGray);
                }
                else {
                    btn.setText("");
                    btn.setBackground(new JButton().getBackground());
                }
            }
        }
    }

    private void UpdateGridStatusBasedOnUISelection() {
        for (Component component : panel.getComponents()) {
            if (component.getClass().equals(JButton.class)) {
                JButton btn = (JButton)component;
                String[] cellXY = component.getName().split(":");
                int x = Integer.parseInt(cellXY[0]);
                int y = Integer.parseInt(cellXY[1]);
                if (btn.getText().equalsIgnoreCase("")) {
                    this.myGrid.UpdateGrid(x, y, false);
                } else {
                    this.myGrid.UpdateGrid(x, y, true);
                }
            }
        }
    }
}
