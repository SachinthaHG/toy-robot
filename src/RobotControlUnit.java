import com.rea.robot.activities.Robot;
import com.rea.robot.activities.Table;
import com.rea.robot.configs.AppConstants;
import com.rea.robot.configs.Directions;
import com.rea.robot.io.ConsoleIO;

/**
 * This class act as the Control unit of the Robot
 */
public class RobotControlUnit {
    public static void main(String[] args) {
        ConsoleIO.getInstance().write("Gird size: " + AppConstants.tableCols + "x" + AppConstants.tableRows);
        /*initialize a table and a robot*/
        Table table = new Table(AppConstants.tableRows, AppConstants.tableCols).setObstacles(4, 3).setObstacles(3,3).setObstacles(2,3).setObstacles(1,3);
        Robot robot = new Robot(table);

        robot.place(0, 0, Directions.SOUTH);
        robot.aStarPathFind(4, 4);

        String input = ConsoleIO.getInstance().read();
        String[] processedInput = input.split(" ");

        /*Exit the application if the command is EXIT*/
        while (!processedInput[0].trim().equals("EXIT")) {
            switch (processedInput[0].trim()) {
                case "PLACE":
                    if (processedInput.length != 2) {
                        ConsoleIO.getInstance().write("Wrong command! please make sure your command have the x coordinate, y coordinate and direction!");
                        break;
                    }

                    String[] placeParams = processedInput[1].split(",");

                    if (placeParams.length != 3) {
                        ConsoleIO.getInstance().write("Wrong command! please make sure your command have the x coordinate, y coordinate and direction!");
                        break;
                    }

                    try {
                        int currentCol = Integer.parseInt(placeParams[0].trim());
                        int currentRow = Integer.parseInt(placeParams[1].trim());
                        String directionString = placeParams[2].trim();
                        Directions direction = null;

                        switch (directionString) {
                            case "NORTH":
                                direction = Directions.NORTH;
                                break;
                            case "EAST":
                                direction = Directions.EAST;
                                break;
                            case "SOUTH":
                                direction = Directions.SOUTH;
                                break;
                            case "WEST":
                                direction = Directions.WEST;
                                break;
                            default:
                                ConsoleIO.getInstance().write("Wrong command! please make sure your command have one of these directions -> NORTH/EAST/SOUTH/WEST");
                                break;
                        }
                        robot.place(currentRow, currentCol, direction);
                    } catch (NumberFormatException e) {
                        ConsoleIO.getInstance().write("Please enter integers for x and y coordinates!");
                    }
                    break;
                case "MOVE":
                    robot.move();
                    break;
                case "LEFT":
                    robot.left();
                    break;
                case "RIGHT":
                    robot.right();
                    break;
                case "REPORT":
                    ConsoleIO.getInstance().write(robot.report());
                    break;
                default:
                    ConsoleIO.getInstance().write("Wrong command! Only use one of these commands (make sure the commands are in uppercase letters) -> PLACE X,Y,F/MOVE/LEFT/RIGHT/REPORT/EXIT");
                    break;
            }

            input = ConsoleIO.getInstance().read();
            processedInput = input.split(" ");
        }

        ConsoleIO.getInstance().close();
    }
}
