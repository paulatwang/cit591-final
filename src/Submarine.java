public class Submarine extends Ship {

    /**
     * Sets the inherited <code>length</code> variable and initializes the
     * <code>hit</code> array, based on the size of this ship (1 tiles).
     */
    public Submarine(){

    }

    /**
     * Specified by <code>getShipType</code> in class <code>Ship</code>>
     *
     * @return "Submarine", indicating that this is a Submarine.
     */
    @Override
    public String getShipType() {
        return "Submarine";
    }
}
