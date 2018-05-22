package com.rea.robot.activities;

import com.rea.robot.configs.AppConstants;
import com.rea.robot.configs.Directions;
import com.rea.robot.io.ConsoleIO;

/**
 * This class simulates the Robot
 */
public class Robot {
    private int currentRow;
    private int currentCol;
    private Directions currentDirection;
    private int[][] table;

    public Robot(Table table) {
        this.table = table.getTable();
    }

    /**
     * This method place the robot on specified position on the table
     *
     * @param row       y coordinate
     * @param col       x coordinate
     * @param direction direction which the robot is facing
     */
    public void place(int row, int col, Directions direction) {
        this.currentRow = AppConstants.tableRows - 1 - row; // align the indexing of given coordinate system in the problem description and indexing of java 2D int array
        this.currentCol = col;
        this.currentDirection = direction;
    }

    /**
     * This method move the robot by one unit towards the direction which the robot is facing
     */
    public void move() {
        switch (currentDirection) {
            case NORTH:
                if (isOnTable(currentRow - 1, currentCol)) {
                    currentRow--;
                } else {
                    ConsoleIO.getInstance().write("Ignoring command! Invalid movement. Out of the table!");
                }
                break;
            case EAST:
                if (isOnTable(currentRow, currentCol + 1)) {
                    currentCol++;
                } else {
                    ConsoleIO.getInstance().write("Ignoring command! Invalid movement. Out of the table!");
                }
                break;
            case SOUTH:
                if (isOnTable(currentRow + 1, currentCol)) {
                    currentRow++;
                } else {
                    ConsoleIO.getInstance().write("Ignoring command! Invalid movement. Out of the table!");
                }
                break;
            case WEST:
                if (isOnTable(currentRow, currentCol - 1)) {
                    currentCol--;
                } else {
                    ConsoleIO.getInstance().write("Ignoring command! Invalid movement. Out of the table!");
                }
                break;
            default:
                break;
        }
    }

    /**
     * This method turn the robot to left
     */
    public void left() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = Directions.WEST;
                break;
            case EAST:
                currentDirection = Directions.NORTH;
                break;
            case SOUTH:
                currentDirection = Directions.EAST;
                break;
            case WEST:
                currentDirection = Directions.SOUTH;
                break;
            default:
                break;
        }
    }

    /**
     * This method turn the robot to right
     */
    public void right() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = Directions.EAST;
                break;
            case EAST:
                currentDirection = Directions.SOUTH;
                break;
            case SOUTH:
                currentDirection = Directions.WEST;
                break;
            case WEST:
                currentDirection = Directions.NORTH;
                break;
            default:
                break;
        }
    }

    /**
     * This method report the current position and the direction of the robot
     *
     * @return returns a string which includes the current position and the direction of the robot (ex: x, y, NORTH)
     */
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(currentCol).append(", ").append(AppConstants.tableRows - 1 - currentRow).append(", ").append(currentDirection).toString();
    }

    /**
     * @param row y coordinate
     * @param col x coordinate
     * @return boolean value that the given position is on the table or not
     */
    private boolean isOnTable(int row, int col) {
        if (row < 0 || col < 0 || row >= AppConstants.tableRows || col >= AppConstants.tableCols) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is to get the y coordinate of the robot
     *
     * @return y coordinate of the robot
     */
    public int getCurrentRow() {
        return AppConstants.tableRows - 1 - currentRow;
    }

    /**
     * This method is to get the x coordinate of the robot
     *
     * @return x coordinate of the robot
     */
    public int getCurrentCol() {
        return currentCol;
    }

    /**
     * This  method is to get the current direction of the robot
     *
     * @return current direction of the robot
     */
    public Directions getCurrentDirection() {
        return currentDirection;
    }
}
