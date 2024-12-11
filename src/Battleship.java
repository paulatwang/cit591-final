public class Battleship extends Ship {

    /**
     * Sets the inherited <code>length</code> variable and initializes the
     * <code>hit</code> array, based on the size of this ship (4 tiles).
     */
    public Battleship(){
        this.length = 4;
        this.hit = new boolean[]{false, false, false, false};
    }

    /**
     * Specified by <code>getShipType</code> in class <code>Ship</code>>
     *
     * @return "Battleship", indicating that this is a Battleship.
     */
    @Override
    public String getShipType() {
        return "Battleship";
    }
}
