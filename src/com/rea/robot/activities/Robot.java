package com.rea.robot.activities;

import com.rea.robot.configs.AppConstants;
import com.rea.robot.configs.Directions;

/**
 * This class simulates the Robot
 */
public class Robot {
    private int currentX;
    private int currentY;
    private Directions currentDirection;
    private int[][] table;

    public Robot(Table table) {
        this.table = table.getTable();
    }

    /**
     * This method place the robot on specified position on the table
     *
     * @param x         x coordinate
     * @param y         y coordinate
     * @param direction direction which the robot is facing
     */
    public void place(int x, int y, Directions direction) {
        this.currentX = x;
        this.currentY = y;
        this.currentDirection = direction;
    }

    /**
     * This method move the robot by one unit towards the direction which the robot is facing
     */
    public void move() {
        switch (currentDirection) {
            case NORTH:
                if (isOnTable(currentX, currentY + 1)) {
                    currentY++;
                }
            case EAST:
                if (isOnTable(currentX + 1, currentY)) {
                    currentX++;
                }
            case SOUTH:
                if (isOnTable(currentX, currentY - 1)) {
                    currentY--;
                }
            case WEST:
                if (isOnTable(currentX - 1, currentY)) {
                    currentX--;
                }
            default:
                return;
        }
    }

    /**
     * This method turn the robot to left
     */
    public void left() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = Directions.WEST;
            case EAST:
                currentDirection = Directions.NORTH;
            case SOUTH:
                currentDirection = Directions.EAST;
            case WEST:
                currentDirection = Directions.SOUTH;
            default:
                return;
        }
    }

    /**
     * This method turn the robot to right
     */
    public void right() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = Directions.EAST;
            case EAST:
                currentDirection = Directions.SOUTH;
            case SOUTH:
                currentDirection = Directions.WEST;
            case WEST:
                currentDirection = Directions.NORTH;
            default:
                return;
        }
    }

    /**
     * This method report the current position and the direction of the robot
     *
     * @return returns a string which includes the current position and the direction of the robot (ex: x, y, NORTH)
     */
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(currentX).append(", ").append(currentY).append(", ").append(currentDirection).toString();
    }

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean value that the given position is on the table or not
     */
    private boolean isOnTable(int x, int y) {
        if (x < 0 || y < 0 || x >= AppConstants.tableRows || y >= AppConstants.tableCols) {
            return false;
        } else {
            return true;
        }
    }
}
