package com.rea.robot.activities;

import com.rea.robot.configs.AppConstants;
import com.rea.robot.configs.Directions;
import com.rea.robot.io.ConsoleIO;

import java.util.*;

/**
 * This class simulates the Robot
 */
public class Robot {
    private int currentRow;
    private int currentCol;
    private Directions currentDirection;
    private int[][] table;
    private boolean placedOnTable = false; // to check if the robot is placed on the table

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
        if (isOnTable(row, col)) {
            this.currentRow = AppConstants.tableRows - 1 - row; // align the indexing of given coordinate system in the problem description and indexing of java 2D int array
            this.currentCol = col;
            this.currentDirection = direction;
            placedOnTable = true;
        } else {
            ConsoleIO.getInstance().write("Ignoring command! Invalid place. Out of the table!");
        }
    }

    /**
     * This method move the robot by one unit towards the direction which the robot is facing
     */
    public void move() {
        if (placedOnTable) {
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
        } else {
            ConsoleIO.getInstance().write("Ignoring command! Place the robot on the table before move!");
        }

    }

    /**
     * This method turn the robot to left
     */
    public void left() {
        if (placedOnTable) {
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
        } else {
            ConsoleIO.getInstance().write("Ignoring command! Place the robot on the table before call left!");
        }

    }

    /**
     * This method turn the robot to right
     */
    public void right() {
        if (placedOnTable) {
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
        } else {
            ConsoleIO.getInstance().write("Ignoring command! Place the robot on the table before call right!");
        }
    }

    /**
     * This method report the current position and the direction of the robot
     *
     * @return returns a string which includes the current position and the direction of the robot (ex: x, y, NORTH)
     */
    public String report() {
        if (placedOnTable) {
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(currentCol).append(", ").append(AppConstants.tableRows - 1 - currentRow).append(", ").append(currentDirection).toString();
        } else {
            return "Ignoring command! Place the robot on the table before call report!";
        }
    }

    public void aStarPathFind(int goalRow, int goalCol) {
        List<Integer> openList = new ArrayList<>();
        List<Integer> closedList = new ArrayList<>();
        int V = AppConstants.tableRows * AppConstants.tableCols;
        int[] gValues = new int[V];
        int[] fValues = new int[V];
        int[] hValues = new int[V];
        int[] parents = new int[V];

        int goalRowIndex = AppConstants.tableRows - 1 - goalRow;
        int goalColIndex = goalCol;

        int goalIndex = goalColIndex + goalRowIndex * AppConstants.tableCols;
        int sourceIndex = currentCol + currentRow * AppConstants.tableCols;

        for (int i = 0; i < V; i++) {
            fValues[i] = Integer.MAX_VALUE;
            gValues[i] = Integer.MAX_VALUE;
            int iCol = i % AppConstants.tableCols;
            int iRow = (i - iCol) / AppConstants.tableCols;
            hValues[i] = caclulateHValue(iRow, iCol, goalRowIndex, goalColIndex);
        }

        openList.add(sourceIndex);
        fValues[sourceIndex] = 0;
        boolean found = false;

        while (!openList.isEmpty()) {
            int u = findMinFValue(fValues, openList);
            if (u == goalIndex) {
                found = true;
                break;
            }

            openList.remove(openList.indexOf(u));
            closedList.add(u);

            int uCol = u % AppConstants.tableCols;
            int uRow = (u - uCol) / AppConstants.tableCols;
            List<List<Integer>> neighbours = findNeighbours(uRow, uCol);

            for (List<Integer> neighbour : neighbours) {
                int k = neighbour.get(1) + neighbour.get(0) * AppConstants.tableCols;

                if (closedList.contains(k)) {
                    continue;
                }

                if (!openList.contains(k)) {
                    openList.add(k);
                }

                if (gValues[k] <= gValues[u] + 10) {
                    continue;
                }

                parents[k] = u;
                gValues[k] = gValues[u] + 10;
                fValues[k] = gValues[k] + hValues[k];
            }
        }

        if (found) {
            findPath(parents, goalIndex, sourceIndex);
            System.out.println(parents[goalIndex]);
        } else {
            System.out.println("No path found!");
        }

    }

    private int findMinFValue(int[] fValues, List<Integer> openList) {
        int minF = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < fValues.length; i++) {
            if (openList.contains(i)) {
                if (minF > fValues[i]) {
                    minF = fValues[i];
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    private int caclulateHValue(int row, int col, int goalRow, int goalCol) {
        return Math.abs(row - goalRow) + Math.abs(col - goalCol);
    }

    public void dijkstraPathFind(int goalRow, int goalCol) {
        int goalRowIndex = AppConstants.tableRows - 1 - goalRow;
        int goalColIndex = goalCol;

        int goalIndex = goalColIndex + goalRowIndex * AppConstants.tableCols;
        int sourceIndex = currentCol + currentRow * AppConstants.tableCols;

        int V = AppConstants.tableRows * AppConstants.tableCols;
        int[] dist = new int[V];

        boolean[] visited = new boolean[V];
        int[] parents = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;

            visited[i] = false;
            parents[i] = -1;
        }

        dist[sourceIndex] = 0;

        int u;
        boolean finished = false;
        for (int i = 0; i < V - 1; i++) {
            u = findMinIndex(dist, visited);

            visited[u] = true;
            int col = u % AppConstants.tableCols;
            int row = (u - col) / AppConstants.tableCols;
            List<List<Integer>> neighbours = findNeighbours(row, col);

            int k;

            for (int j = 0; j < neighbours.size(); j++) {
                k = neighbours.get(j).get(1) + neighbours.get(j).get(0) * AppConstants.tableCols;
                if (!visited[k] && 1 + dist[u] < dist[k]) {
                    dist[k] = 1 + dist[u];
                    parents[k] = u;
                }

                if (k == goalIndex) {
                    finished = true;
                    break;
                }
            }

            if (finished) {
                break;
            }
        }

        findPath(parents, goalIndex, sourceIndex);
        System.out.println(dist[goalIndex]);
    }

    private void findPath(int[] parents, int goalIndex, int sourceIndex) {
        Stack<Integer> stack = new Stack<>();
        int node = goalIndex;
        stack.push(goalIndex);
        while (node != sourceIndex) {
            stack.push(parents[node]);
            node = parents[node];
        }

        StringBuilder stringBuilder = new StringBuilder();
        int currentNode = stack.pop();
        int nextNode;

        while (!stack.isEmpty()) {
            nextNode = stack.pop();
            switch (currentDirection) {
                case NORTH:
                    if (nextNode == currentNode - AppConstants.tableCols) {
                        stringBuilder.append("MOVE").append("\n");
                    } else if (nextNode == currentNode - 1) {
                        stringBuilder.append("LEFT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.WEST;
                    } else if (nextNode == currentNode + 1) {
                        stringBuilder.append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.EAST;
                    } else if (nextNode == currentNode + AppConstants.tableCols) {
                        stringBuilder.append("RIGHT").append("\n").append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.SOUTH;
                    }
                    break;
                case EAST:
                    if (nextNode == currentNode - AppConstants.tableCols) {
                        stringBuilder.append("LEFT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.NORTH;
                    } else if (nextNode == currentNode - 1) {
                        stringBuilder.append("RIGHT").append("\n").append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.WEST;
                    } else if (nextNode == currentNode + 1) {
                        stringBuilder.append("MOVE").append("\n");
                    } else if (nextNode == currentNode + AppConstants.tableCols) {
                        stringBuilder.append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.SOUTH;
                    }
                    break;
                case SOUTH:
                    if (nextNode == currentNode - AppConstants.tableCols) {
                        stringBuilder.append("RIGHT").append("\n").append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.NORTH;
                    } else if (nextNode == currentNode - 1) {
                        stringBuilder.append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.WEST;
                    } else if (nextNode == currentNode + 1) {
                        stringBuilder.append("LEFT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.EAST;
                    } else if (nextNode == currentNode + AppConstants.tableCols) {
                        stringBuilder.append("MOVE").append("\n");
                    }
                    break;
                case WEST:
                    if (nextNode == currentNode - AppConstants.tableCols) {
                        stringBuilder.append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.NORTH;
                    } else if (nextNode == currentNode - 1) {
                        stringBuilder.append("MOVE").append("\n");
                    } else if (nextNode == currentNode + 1) {
                        stringBuilder.append("RIGHT").append("\n").append("RIGHT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.EAST;
                    } else if (nextNode == currentNode + AppConstants.tableCols) {
                        stringBuilder.append("LEFT").append("\n").append("MOVE").append("\n");
                        currentDirection = Directions.SOUTH;
                    }
                    break;
                default:
                    break;
            }
            currentNode = nextNode;
        }

        System.out.println(stringBuilder.toString());
    }

    private int findMinIndex(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private List<List<Integer>> findNeighbours(int row, int col) {
        List<List<Integer>> neighbours = new ArrayList<>();

        if (isOnTable(row - 1, col)) {
            List<Integer> n1 = new ArrayList<>();
            n1.add(row - 1);
            n1.add(col);
            neighbours.add(n1);
        }

        if (isOnTable(row, col - 1)) {
            List<Integer> n2 = new ArrayList<>();
            n2.add(row);
            n2.add(col - 1);
            neighbours.add(n2);
        }

        if (isOnTable(row + 1, col)) {
            List<Integer> n3 = new ArrayList<>();
            n3.add(row + 1);
            n3.add(col);
            neighbours.add(n3);
        }

        if (isOnTable(row, col + 1)) {
            List<Integer> n4 = new ArrayList<>();
            n4.add(row);
            n4.add(col + 1);
            neighbours.add(n4);
        }
        return neighbours;
    }


    /**
     * @param row y coordinate
     * @param col x coordinate
     * @return boolean value that the given position is on the table or not
     */
    private boolean isOnTable(int row, int col) {
        if ((row < 0 || col < 0 || row >= AppConstants.tableRows || col >= AppConstants.tableCols)) {
            return false;
        } else if (table[row][col] == 1) {
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
