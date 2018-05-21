package com.rea.robot.activities;

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

    }

    /**
     * This method move the robot by one unit towards the direction which the robot is facing
     */
    public void move() {

    }

    /**
     * This method turn the robot to left
     */
    public void left() {

    }

    /**
     * This method turn the robot to right
     */
    public void right() {

    }

    /**
     * This method report the current position and the direction of the robot
     *
     * @return returns a string which includes the current position and the direction of the robot (ex: x, y, NORTH)
     */
    public String report() {
        return null;
    }
}
