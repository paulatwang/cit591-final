import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the main class of the Battleship game. This is where we set
 *  up the game, accept shots from the user as coordinates, display the results,
 *  print final scores, and ask the user if they want to play again.
 *
 * @author paula wang
 *
 */
public class BattleshipGame {
    /**
     * An instance of the Ocean class
     */
    private static Ocean ocean;

    /**
     * used for obtaining user input
     */
    private static Scanner input;

    public static void main(String[] args) {
        while (true) {
            // reset objects
            ocean = new Ocean();
            input = new Scanner(System.in);

            // generate random placement of ships in ocean
            ocean.placeAllShipsRandomly();

            // start game
            printGreeting();
            while (!ocean.isGameOver()) {
                int[] coordinates = getCoordinates(); // prompt user for next coordinates
                addShot(coordinates[0], coordinates[1]); // add shot to ocean
            }
            // end game
            printEnding();

            // ask about replay
            if (!replay()) {
                break;
            }

        }

    }

    /**
     * print welcoming and instructions message to player
     */
    private static void printGreeting() {
        System.out.println("""
                ▗▄▄▖  ▗▄▖▗▄▄▄▖▗▄▄▄▖▗▖   ▗▄▄▄▖ ▗▄▄▖▗▖ ▗▖▗▄▄▄▖▗▄▄▖
                ▐▌ ▐▌▐▌ ▐▌ █    █  ▐▌   ▐▌   ▐▌   ▐▌ ▐▌  █  ▐▌ ▐▌
                ▐▛▀▚▖▐▛▀▜▌ █    █  ▐▌   ▐▛▀▀▘ ▝▀▚▖▐▛▀▜▌  █  ▐▛▀▘
                ▐▙▄▞▘▐▌ ▐▌ █    █  ▐▙▄▄▖▐▙▄▄▖▗▄▄▞▘▐▌ ▐▌▗▄█▄▖▐▌
                """);
        System.out.println("Welcome to the High Seas, Captain!!\n");
        System.out.println("The ocean is calm, but don’t be fooled — danger lurks beneath the waves.");
        System.out.println("The enemy is out there... only your sharp aim and strategy will save us.");
        System.out.println("We did some scouting and found that the enemy's fleet has 10 ships:\n");
        System.out.println("    - 1 Battleship:     <XXXX>      (length 4)");
        System.out.println("    - 2 Cruisers:        <XXX>      (length 3)");
        System.out.println("    - 3 Destroyers:       <XX>      (length 2)");
        System.out.println("    - 4 Submarines:        <X>      (length 1)\n");
        System.out.println("Your mission? To sink every last ship that threatens our waters!");
        System.out.println("May your aim be true and the winds ever in your favor.\n");
        ocean.print(); // print ocean
    }

    /**
     * Prompts player for coordinates to hit
     *
     * @return coordinates in an int array
     */
    private static int[] getCoordinates() {
        while (true) {
            System.out.println("Pick the coordinates for your next target.");
            System.out.print("Enter row: ");
            String row = input.next();
            System.out.print("Enter column: ");
            String col = input.next();

            if (isValidInput(row, col)) {
                return new int[]{Integer.parseInt(row), Integer.parseInt(col)};
            } else {
                System.out.println("Invalid input. Enter a valid set of coordinates.");
            }
        }
    }

    /**
     * Checks whether the coordinates provided by user input are valid or not.
     *
     * @param row the row of the coordinate
     * @param col the column of the coordinate
     * @return {@literal true} if valid, {@literal false} if not
     */
    private static boolean isValidInput (String row, String col){
        return row.matches("^[0-9]$") && col.matches("^[0-9]$");
    }

    /**
     * Adds the chosen coordinates as a shot in the ocean.
     * <ul>
     *     <li>if hits ship, print "You landed a hit!"</li>
     *     <li>if sunk ship, print "Wow! You sunk a <code>shiptype</code>! \n"</li>
     *     <li>if hit nothing, print "Aww, nothing but open water..."</li>
     * </ul>
     *
     * @param row the row of the shot
     * @param column the column of the shot
     */
    private static void addShot ( int row, int column){
        Ship shipAtLocation = ocean.getShipArray()[row][column];

        if (ocean.shootAt(row, column)) { // ship present and not sunk
            boolean stillStanding = shipAtLocation.shootAt(row, column);
            ocean.print();
            System.out.println("You landed a hit!");
            if (!stillStanding) { // if sunk after getting hit
                String shipType = shipAtLocation.getShipType();
                System.out.printf("Wow! You sunk a %s! \n", shipType);
            }
        } else {
            ocean.print();
            System.out.println("Aww, nothing but open water...");
        }
    }

    /**
     * Prints out the ending message, including total shots fired and hit count
     */
    private static void printEnding () {
        System.out.println();
        System.out.println("Mission complete! The enemy fleet is no more.");
        System.out.println("     _______________________");
        System.out.println("    |    Game Statistics     |");
        System.out.printf("    | Shots fired ------ %3d | \n", ocean.getShotsFired());
        System.out.printf("    | Hit count -------- %3d | \n", ocean.getHitCount());
        System.out.println("     _______________________");
    }

    /**
     * Prompts the user to see if they want to replay the game
     *
     * @return {@literal true} if yes, {@literal false} if no
     */
    private static boolean replay() {
        while (true) {
            System.out.println("Would you like to play again? <y> or <n>");
            String ans = input.next().trim().toLowerCase();
            if (ans.equals("y")) {
                return true;
            } else if (ans.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid response. Please respond with <y> or <n>.");
            }
        }
    }
}


