package com.rea.robot.activities;

import com.rea.robot.configs.AppConstants;

/**
 * This class simulates the tabletop
 */
public class Table {
    private int[][] table; // data structure to simulate the tabletop

    public Table(int rows, int cols) {
        table = new int[rows][cols]; // initialize the tabletop with the given dimensions
    }

    /**
     * Getter method to get the table
     *
     * @return table (a 2D int array)
     */
    public int[][] getTable() {
        return table;
    }

    public Table setObstacles(int row, int col) {
        table[AppConstants.tableRows - 1 - row][col] = 1;
        return this;
    }
}
