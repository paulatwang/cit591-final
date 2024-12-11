import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleshipGame {
    private Ocean ocean;
    private Scanner input;

    public BattleshipGame() {
        // initialize fields
        this.ocean = new Ocean();
        this.input = new Scanner(System.in);

        // generate random placement of ships in ocean
        ocean.placeAllShipsRandomly();
    }

    public void play() {
        printGreeting();
        while (!ocean.isGameOver()) {
            int[] coordinates = getCoordinates(); // prompt user for next coordinates
            addShot(coordinates[0], coordinates[1]); // add shot to ocean
        }
        printEnding();
    }


    private void printGreeting() {
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

    private int[] getCoordinates() {
        try {
            System.out.println("Pick the coordinates for your next target.");
            System.out.print("Enter row: ");
            String row = input.next();
            System.out.print("Enter column: ");
            String col = input.next();

            if (isValidInput(row, col)) {
                return new int[]{Integer.parseInt(row), Integer.parseInt(col)};
            } else throw new InputMismatchException();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Enter a valid set of coordinates.");
            return getCoordinates();
        }
    }


    private boolean isValidInput(String row, String col) {
        return row.matches("^[0-9]$") && col.matches("^[0-9]$");
    }


    private void addShot(int row, int column) {
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

    private void printEnding() {
        System.out.println();
        System.out.println("Mission complete! The enemy fleet is no more.");
        System.out.println("     _______________________");
        System.out.println("    |    Game Statistics     |");
        System.out.printf("    | Shots fired ------ %3d | \n", ocean.getShotsFired());
        System.out.printf("    | Hit count -------- %3d | \n", ocean.getHitCount());
        System.out.println("     _______________________");
    }
}

