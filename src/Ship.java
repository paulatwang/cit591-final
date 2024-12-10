/**
 * Ship is the abstract class for all of the ships and sea tiles
 * that will make up the game of Battleship. Ships of all kinds are
 * always considered to be facing up or to the left, meaning that any
 * portion of the ship that is not the bow will be at a higher numbered
 * row or column than the bow.
 */



public abstract class Ship {

    /**
     * The column (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowColumn;

    /**
     * The row (0 to 9) which contains the bow (front) of the ship.
     */
    protected int bowRow;

    /**
     * hit is an array of four booleans telling whether that part of the ship has been hit.
     */
    protected boolean[] hit;

    /**
     * {@literal true}  if the ship occupies a single row,
     * {@literal false}  otherwise.
     */
    protected boolean horizontal;

    /**
     * The number of tiles occupied by the ship.
     */
    protected int length;

    /**
     * Creates an instance of a ship.
     */
    public Ship(){

    }

    /**
     * @return the length of the ship
     */
    public int getLength(){
        return length;
    }

    /**
     * @return the row of the bow (front) of the ship
     */
    public int getBowRow(){
        return bowRow;
    }

    /**
     * @return the column of the bow (front) of the ship
     */
    public int getBowColumn(){
        return bowColumn;
    }

    /**
     * @param bowColumn the bowColumn to set
     */
    public void setBowColumn(int bowColumn){
        return;
    }

    /**
     * @return {@literal true} if this boat is horizontal (facing left),
     *         {@literal false} otherwise.
     */
    public boolean isHorizontal(){
        return false;
    }

    /**
     * @param horizontal the horizontal to set
     */
    public void setHorizontal(boolean horizontal){
        return;
    }

    /**
     * @param bowRow the bowRow to set
     */
    public void setBowRow(int bowRow){
        return;
    }

    /**
     * @return the <code></>String</code> representing the type of this ship.
     */
    public abstract String getShipType();

    /**
     * Determines whether or not this is represents a valid placement
     * configuration for this Ship in this Ocean. Ship objects in an Ocean
     * must not overlap other Ship objects or touch them vertically, horizontally,
     * or diagonally. Additionally, the placement cannot be such that the Ship
     * would extend beyond the extents of the 2D array in which it is placed.
     * <b>Calling this method should not actually change either the Ship or the provided Ocean.</b>
     *
     * @param row the candidate row to place the ship
     * @param column  the candidate column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean the Ocean in which this ship might be placed
     * @return {@literal true} if it is valid to place this ship of this length
     *          in this location with this orientation, and
     *          {@literal false} otherwise.
     */
    public boolean okToPlaceShipAt(int row,
                                   int column,
                                   boolean horizontal,
                                   Ocean ocean){
        return false;
    }


    /**
     * Puts the Ship in the Ocean. This will give values to the <code>bowRow</code>,
     * <code>bowColumn</code>, and <code>horizontal</code> instance variables in the Ship.
     * This should also place a reference to this Ship in each of the one or more
     * locations (up to four) in the corresponding Ocean array this Ship is
     * being placed in. Each of the references placed in the Ocean will be identical
     * since it is not possible to refer to a "part" of a ship, only the whole ship.
     *
     * @param row the row to place the ship
     * @param column the column to place the ship
     * @param horizontal whether or not to have the ship facing to the left
     * @param ocean the Ocean in which this ship will be placed
     */
    public void placeShipAt(int row,
                            int column,
                            boolean horizontal,
                            Ocean ocean){
        return;
    }

    /**
     * If a part of this ship occupies this coordinate, and if the ship hasn't
     * been sunk, mark the part of the ship at that coordinate as "hit".
     *
     * @param row the row of the shot
     * @param column the column of the shot
     * @return {@literal true} if this ship hasn't been sunk and a part
     *          of this ship occupies the given row and column and
     *          {@literal false} otherwise.
     */
    public boolean shootAt(int row,
                           int column){
        return false;
    }

    /**
     * Returns {@literal true} if this ship has been sunk and {@literal false} otherwise.
     *
     * @return @literal true}  if every part of the ship has been hit and
     *          {@literal false} otherwise.
     */
    public boolean isSunk(){
        return false;
    }

    /**
     * Returns a single character String to use in the Ocean's print method.
     * This method should return "x" if the ship has been sunk, and "S" if
     * it has not yet been sunk. This method can only be used to print out
     * locations in the ocean that have been shot at; it should not be used to
     * print locations that have not been the target of a shot yet.
     *
     * @return "x" if this ship has been sunk, and "S" otherwise.
     */
    public String toString(){
        if (isSunk()){
            return "x";
        }
        return "S";
    }

}
