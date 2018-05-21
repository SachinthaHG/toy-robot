package com.rea.robot.activities;

/**
 * This class simulates the tabletop
 */
public class Table {
    private int[][] table; // data structure to simulate the tabletop

    public Table(int rows, int cols) {
        table = new int[rows][cols]; // initialize the tabletop with the given dimensions
    }

    public int[][] getTable() {
        return table;
    }
}
