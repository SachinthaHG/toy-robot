package com.rea.robot.activities;

import com.rea.robot.configs.Directions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RobotTest {
    Table table;
    Robot robot;

    @Before
    public void setUp() {
        table = new Table(5, 5);
        robot = new Robot(table);
    }

    @Test
    public void testPlaceFaceNorth() {
        int expectedRow = 1;
        int expectedCol = 2;
        Directions expectedDirection = Directions.NORTH;

        robot.place(1, 2, Directions.NORTH);
        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testPlaceFaceEast() {
        int expectedRow = 2;
        int expectedCol = 4;
        Directions expectedDirection = Directions.EAST;

        robot.place(2, 4, Directions.EAST);
        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testPlaceFaceSouth() {
        int expectedRow = 4;
        int expectedCol = 1;
        Directions expectedDirection = Directions.SOUTH;

        robot.place(4, 1, Directions.SOUTH);
        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testPlaceFaceWest() {
        int expectedRow = 0;
        int expectedCol = 0;
        Directions expectedDirection = Directions.WEST;

        robot.place(0, 0, Directions.WEST);

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceNorth() {
        robot.place(1, 2, Directions.NORTH);

        int expectedRow = 2;
        int expectedCol = 2;
        Directions expectedDirection = Directions.NORTH;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceNorthOutOfTheTable() {
        robot.place(4, 2, Directions.NORTH);

        int expectedRow = 4;
        int expectedCol = 2;
        Directions expectedDirection = Directions.NORTH;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceEast() {
        robot.place(1, 2, Directions.EAST);

        int expectedRow = 1;
        int expectedCol = 3;
        Directions expectedDirection = Directions.EAST;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceEastOutOfTheTable() {
        robot.place(4, 4, Directions.EAST);

        int expectedRow = 4;
        int expectedCol = 4;
        Directions expectedDirection = Directions.EAST;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceSouth() {
        robot.place(1, 2, Directions.SOUTH);

        int expectedRow = 0;
        int expectedCol = 2;
        Directions expectedDirection = Directions.SOUTH;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceSouthOutOfTheTable() {
        robot.place(0, 0, Directions.SOUTH);

        int expectedRow = 0;
        int expectedCol = 0;
        Directions expectedDirection = Directions.SOUTH;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceWest() {
        robot.place(1, 2, Directions.WEST);

        int expectedRow = 1;
        int expectedCol = 1;
        Directions expectedDirection = Directions.WEST;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testMoveFaceWestOutOfTheTable() {
        robot.place(0, 0, Directions.WEST);

        int expectedRow = 0;
        int expectedCol = 0;
        Directions expectedDirection = Directions.WEST;

        robot.move();

        assertEquals(expectedRow, robot.getCurrentRow());
        assertEquals(expectedCol, robot.getCurrentCol());
        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testLeftFaceNorth() {
        robot.place(2, 3, Directions.NORTH);
        Directions expectedDirection = Directions.WEST;
        robot.left();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testLeftFaceEast() {
        robot.place(2, 3, Directions.EAST);
        Directions expectedDirection = Directions.NORTH;
        robot.left();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testLeftFaceSouth() {
        robot.place(2, 3, Directions.SOUTH);
        Directions expectedDirection = Directions.EAST;
        robot.left();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testLeftFaceWest() {
        robot.place(2, 3, Directions.WEST);
        Directions expectedDirection = Directions.SOUTH;
        robot.left();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testRightFaceNorth() {
        robot.place(2, 3, Directions.NORTH);
        Directions expectedDirection = Directions.EAST;
        robot.right();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testRightFaceEast() {
        robot.place(2, 3, Directions.EAST);
        Directions expectedDirection = Directions.SOUTH;
        robot.right();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testRightFaceSouth() {
        robot.place(2, 3, Directions.SOUTH);
        Directions expectedDirection = Directions.WEST;
        robot.right();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }

    @Test
    public void testRightFaceWest() {
        robot.place(2, 3, Directions.WEST);
        Directions expectedDirection = Directions.NORTH;
        robot.right();

        assertEquals(expectedDirection, robot.getCurrentDirection());
    }
}
