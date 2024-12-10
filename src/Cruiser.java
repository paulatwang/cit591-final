public class Cruiser extends Ship {

    /**
     * Sets the inherited <code>length</code> variable and initializes the
     * <code>hit</code> array, based on the size of this ship (3 tiles).
     */
    public Cruiser(){

    }

    /**
     * Specified by <code>getShipType</code> in class <code>Ship</code>>
     *
     * @return "Cruiser", indicating that this is a Cruiser.
     */
    @Override
    public String getShipType() {
        return "Cruiser";
    }
}