import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class BattleshipTest {
    private Ocean ocean;
    private Battleship battleship;
    private Cruiser cruiser;
    private Destroyer destroyer;
    private Submarine submarine;
    private EmptySea emptysea;
    static ByteArrayOutputStream outputStream;
    static PrintStream originalSystemOut;

    @BeforeAll
    public static void outputStreams(){
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    public void setUp() {
        ocean = new Ocean();
        battleship = new Battleship();
        cruiser = new Cruiser();
        destroyer = new Destroyer();
        submarine = new Submarine();
        emptysea = new EmptySea();
        outputStream.reset();

        // create EmptySea Ship[][] array
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                ocean.getShipArray()[row][col] = new EmptySea();
            }
        }
    }

    @AfterAll
    public static void restore(){
        System.setOut(originalSystemOut);
    }


    // test ship methods
    @Test
    public void testShipGetterSetterMethods(){
        battleship.setBowRow(1);
        battleship.setBowColumn(2);
        battleship.setHorizontal(true);
        assertEquals(1, battleship.getBowRow());
        assertEquals(2, battleship.getBowColumn());
        assertTrue(battleship.isHorizontal());

        battleship.setBowRow(-5);
        battleship.setBowColumn(-8);
        assertEquals(-5, battleship.getBowRow());
        assertEquals(-8, battleship.getBowColumn());

        battleship.setBowRow(0);
        battleship.setBowColumn(0);
        battleship.setHorizontal(false);
        assertEquals(0, battleship.getBowRow());
        assertEquals(0, battleship.getBowColumn());
        assertFalse(battleship.isHorizontal());
    }

    @Test
    public void testGetLengthForAllShips(){
        assertEquals(4, battleship.getLength());
        assertEquals(3, cruiser.getLength());
        assertEquals(2, destroyer.getLength());
        assertEquals(1, submarine.getLength());
        assertEquals(1, emptysea.getLength());
    }

    @Test
    public void testGetShipTypeForAllShips(){
        assertEquals("Battleship", battleship.getShipType());
        assertEquals("Cruiser", cruiser.getShipType());
        assertEquals("Destroyer", destroyer.getShipType());
        assertEquals("Submarine", submarine.getShipType());
        assertEquals("empty", emptysea.getShipType());
    }

    @Test
    public void testOkToPlaceShipAtIfExceedsGrid(){

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                // test borders for horizontal ships
                switch (col) {
                    case 9:
                        assertFalse(destroyer.okToPlaceShipAt(row, col, true, ocean));
                        assertFalse(cruiser.okToPlaceShipAt(row, col, true, ocean));
                        assertFalse(battleship.okToPlaceShipAt(row, col, true, ocean));
                        break;
                    case 8:
                        assertFalse(cruiser.okToPlaceShipAt(row, col, true, ocean));
                        assertFalse(battleship.okToPlaceShipAt(row, col, true, ocean));
                        break;
                    case 7:
                        assertFalse(battleship.okToPlaceShipAt(row, col, true, ocean));
                        break;
                    default:
                        assertTrue(submarine.okToPlaceShipAt(row, col, true, ocean));
                        assertTrue(destroyer.okToPlaceShipAt(row, col, true, ocean));
                        assertTrue(cruiser.okToPlaceShipAt(row, col, true, ocean));
                        assertTrue(battleship.okToPlaceShipAt(row, col, true, ocean));
                }
                // test borders for vertical ships
                switch (row) {
                    case 9:
                        assertFalse(destroyer.okToPlaceShipAt(row, col, false, ocean));
                        assertFalse(cruiser.okToPlaceShipAt(row, col, false, ocean));
                        assertFalse(battleship.okToPlaceShipAt(row, col, false, ocean));
                        break;
                    case 8:
                        assertFalse(cruiser.okToPlaceShipAt(row, col, false, ocean));
                        assertFalse(battleship.okToPlaceShipAt(row, col, false, ocean));
                        break;
                    case 7:
                        assertFalse(battleship.okToPlaceShipAt(row, col, false, ocean));
                        break;
                    default:
                        assertTrue(submarine.okToPlaceShipAt(row, col, false, ocean));
                        assertTrue(destroyer.okToPlaceShipAt(row, col, false, ocean));
                        assertTrue(cruiser.okToPlaceShipAt(row, col, false, ocean));
                        assertTrue(battleship.okToPlaceShipAt(row, col, false, ocean));
                }
            }
        }
    }

    @Test
    public void testOkToPlaceShipAtForSubmarines() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // place ships surrounding 2x2 grid
                if (row > 1 || col > 1){
                    ocean.getShipArray()[row][col] = new Submarine();
                }
            }
        }
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (row == 0 && col == 0){
                    assertTrue(submarine.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(submarine.okToPlaceShipAt(row, col, false, ocean));
                } else {
                    assertFalse(submarine.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(submarine.okToPlaceShipAt(row, col, false, ocean));
                }
            }
        }
    }


    @Test
    public void testOkToPlaceShipAtForDestroyers() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                // place ships surrounding 3x3 grid
                if (row > 2 || col > 2){
                    ocean.getShipArray()[row][col] = new Submarine();
                }
            }
        }
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (row == 0 && col == 0) {
                    assertTrue(destroyer.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(destroyer.okToPlaceShipAt(row, col, false, ocean));
                } else if (row == 1 && col == 0){
                    assertTrue(destroyer.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(destroyer.okToPlaceShipAt(row, col, false, ocean));
                } else if (row == 0 && col == 1) {
                    assertFalse(destroyer.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(destroyer.okToPlaceShipAt(row, col, false, ocean));
                } else {
                    assertFalse(destroyer.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(destroyer.okToPlaceShipAt(row, col, false, ocean));
                }
            }
        }
    }


    @Test
    public void testOkToPlaceShipAtForCruisers() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                // place ships surrounding 4x4 grid
                if (row > 3 || col > 3){
                    ocean.getShipArray()[row][col] = new Submarine();
                }
            }
        }
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (row == 0 && col == 0) {
                    assertTrue(cruiser.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(cruiser.okToPlaceShipAt(row, col, false, ocean));
                } else if (row == 1 && col == 0){
                    assertTrue(cruiser.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(cruiser.okToPlaceShipAt(row, col, false, ocean));
                } else if (row == 0 && col == 1) {
                    assertFalse(cruiser.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(cruiser.okToPlaceShipAt(row, col, false, ocean));
                } else {
                    assertFalse(cruiser.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(cruiser.okToPlaceShipAt(row, col, false, ocean));
                }
            }
        }
    }

    @Test
    public void testOkToPlaceShipAtForBattleships() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // place ships surrounding 4x4 grid
                if (row > 4 || col > 4){
                    ocean.getShipArray()[row][col] = new Submarine();
                }
            }
        }
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (row == 0 && col == 0) {
                    assertTrue(battleship.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(battleship.okToPlaceShipAt(row, col, false, ocean));
                } else if (row == 1 && col == 0){
                    assertTrue(battleship.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(battleship.okToPlaceShipAt(row, col, false, ocean));
                } else if (row == 0 && col == 1) {
                    assertFalse(battleship.okToPlaceShipAt(row, col, true, ocean));
                    assertTrue(battleship.okToPlaceShipAt(row, col, false, ocean));
                } else {
                    assertFalse(battleship.okToPlaceShipAt(row, col, true, ocean));
                    assertFalse(battleship.okToPlaceShipAt(row, col, false, ocean));
                }
            }
        }
    }

    @Test
    public void testPlaceShipAt(){
        submarine.placeShipAt(1, 2, true, ocean);
        assertEquals(1, submarine.shipCoordinates.get(0)[0]);
        assertEquals(2, submarine.shipCoordinates.get(0)[1]);

        destroyer.placeShipAt(1, 2, true, ocean);
        assertEquals(1, destroyer.shipCoordinates.get(0)[0]);
        assertEquals(2, destroyer.shipCoordinates.get(0)[1]);
        assertEquals(1, destroyer.shipCoordinates.get(1)[0]);
        assertEquals(3, destroyer.shipCoordinates.get(1)[1]);

        cruiser.placeShipAt(1, 2, true, ocean);
        assertEquals(1, cruiser.shipCoordinates.get(0)[0]);
        assertEquals(2, cruiser.shipCoordinates.get(0)[1]);
        assertEquals(1, cruiser.shipCoordinates.get(1)[0]);
        assertEquals(3, cruiser.shipCoordinates.get(1)[1]);
        assertEquals(1, cruiser.shipCoordinates.get(2)[0]);
        assertEquals(4, cruiser.shipCoordinates.get(2)[1]);

        battleship.placeShipAt(1, 2, true, ocean);
        assertEquals(1, battleship.shipCoordinates.get(0)[0]);
        assertEquals(2, battleship.shipCoordinates.get(0)[1]);
        assertEquals(1, battleship.shipCoordinates.get(1)[0]);
        assertEquals(3, battleship.shipCoordinates.get(1)[1]);
        assertEquals(1, battleship.shipCoordinates.get(2)[0]);
        assertEquals(4, battleship.shipCoordinates.get(2)[1]);
        assertEquals(1, battleship.shipCoordinates.get(3)[0]);
        assertEquals(5, battleship.shipCoordinates.get(3)[1]);
    }

    @Test
    public void testShipShootAtAndIsSunkAndIsString(){
        // submarine before sinking
        submarine.placeShipAt(0,0, true, ocean);
        assertFalse(submarine.isSunk());
        System.out.print(submarine.toString());
        assertEquals("S", outputStream.toString());
        outputStream.reset();
        // shoot at
        assertFalse(submarine.shootAt(0,0));
        // after sinking
        assertTrue(submarine.isSunk());
        System.out.print(submarine.toString());
        assertEquals("x", outputStream.toString());
        outputStream.reset();

        // destroyer before sinking
        destroyer.placeShipAt(0,0, true, ocean);
        assertFalse(destroyer.isSunk());
        System.out.print(destroyer.toString());
        assertEquals("S", outputStream.toString());
        outputStream.reset();
        // shoot at
        assertTrue(destroyer.shootAt(0,0));
        assertFalse(destroyer.shootAt(0,1));
        // after sinking
        assertTrue(destroyer.isSunk());
        System.out.print(destroyer.toString());
        assertEquals("x", outputStream.toString());
        outputStream.reset();

        // cruiser before sinking
        cruiser.placeShipAt(0,0, true, ocean);
        assertFalse(cruiser.isSunk());
        System.out.print(cruiser.toString());
        assertEquals("S", outputStream.toString());
        outputStream.reset();
        // shoot at
        assertTrue(cruiser.shootAt(0,0));
        assertTrue(cruiser.shootAt(0,1));
        assertFalse(cruiser.shootAt(0,2));
        // after sinking
        assertTrue(cruiser.isSunk());
        System.out.print(cruiser.toString());
        assertEquals("x", outputStream.toString());
        outputStream.reset();

        // battleship before sinking
        battleship.placeShipAt(0,0, true, ocean);
        assertFalse(battleship.isSunk());
        System.out.print(battleship.toString());
        assertEquals("S", outputStream.toString());
        outputStream.reset();
        // shoot at
        assertTrue(battleship.shootAt(0,0));
        assertTrue(battleship.shootAt(0,1));
        assertTrue(battleship.shootAt(0,2));
        assertFalse(battleship.shootAt(0,3));
        // after sinking
        assertTrue(battleship.isSunk());
        System.out.print(battleship.toString());
        assertEquals("x", outputStream.toString());
        outputStream.reset();
    }


    // test ocean methods
    @Test
    public void testOceanGetterReturnsConstructorValue(){
        assertEquals(0, ocean.getShotsFired());
        assertEquals(0, ocean.getHitCount());
        assertEquals(0, ocean.getShipsSunk());
        assertEquals(new HashSet<>(), ocean.getCoordinatesHit());

        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                assertEquals(emptysea.getClass(), ocean.getShipArray()[row][col].getClass());
            }
        }
    }

    // test ship placement
    @Test
    public void testPlaceAllShipsRandomly(){
        assertEquals(0, countShipsInOcean(ocean, "Submarine"));
        assertEquals(0, countShipsInOcean(ocean, "Destroyer"));
        assertEquals(0, countShipsInOcean(ocean, "Cruiser"));
        assertEquals(0, countShipsInOcean(ocean, "Battleship"));
        ocean.placeAllShipsRandomly();
        assertEquals(4, countShipsInOcean(ocean, "Submarine"));
        assertEquals(6, countShipsInOcean(ocean, "Destroyer"));
        assertEquals(6, countShipsInOcean(ocean, "Cruiser"));
        assertEquals(4, countShipsInOcean(ocean, "Battleship"));
    }

    /**
     * Helper function for testing placeAllShipsRandomly() method.
     * Counts all parts of a certain ship in an ocean instance.
     *
     * @param ocean the ocean instance the ships are placed in
     * @param shipType the type of ship
     * @return number of ships in the ocean
     */
    private int countShipsInOcean(Ocean ocean, String shipType) {
        int count = 0;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (ocean.getShipArray()[row][col].getShipType().equals(shipType)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void testIsOccupied(){
        int numOccupied = 0;

        // before placing ships
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                if(ocean.isOccupied(row, col)){
                    numOccupied++;
                }
            }
        }
        assertEquals(0, numOccupied);

        // after placing ships
        ocean.placeAllShipsRandomly();
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                if(ocean.isOccupied(row, col)){
                    numOccupied++;
                }
            }
        }
        assertEquals(20, numOccupied);
    }

    @Test
    public void testOceanShootAt(){
        submarine.placeShipAt(0,0,true, ocean);
        assertTrue(ocean.shootAt(0,0)); // hit and sunk
        assertFalse(ocean.shootAt(0,0)); // already sunk

        destroyer.placeShipAt(2,0, true, ocean);
        assertTrue(ocean.shootAt(2, 0)); // hit
        assertTrue(ocean.shootAt(2, 1)); // hit and sunk
        assertFalse(ocean.shootAt(2, 1)); // already sunk

        cruiser.placeShipAt(4,0, true, ocean);
        assertTrue(ocean.shootAt(4, 0)); // hit
        assertTrue(ocean.shootAt(4, 1)); // hit
        assertTrue(ocean.shootAt(4, 2)); // hit and sunk
        assertFalse(ocean.shootAt(4, 3)); // already sunk

        battleship.placeShipAt(6,0, true, ocean);
        assertTrue(ocean.shootAt(6, 0)); // hit
        assertTrue(ocean.shootAt(6, 1)); //  hit
        assertTrue(ocean.shootAt(6, 2)); //  hit
        assertTrue(ocean.shootAt(6, 3)); //  hit and sunk
        assertFalse(ocean.shootAt(6, 4)); // already sunk

    }

    @Test
    public void testGameOverAndPostGameGetterMethods(){
        ocean.placeAllShipsRandomly();
        // test at beginning of game
        assertFalse(ocean.isGameOver());
        // shoot all positions
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                ocean.shootAt(row, col);
            }
        }
        assertTrue(ocean.isGameOver());
        assertEquals(100, ocean.getShotsFired());
        assertEquals(20, ocean.getHitCount());
        assertEquals(10, ocean.getShipsSunk());
    }




}
