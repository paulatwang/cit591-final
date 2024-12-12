import java.util.ArrayList;

public class Cruiser extends Ship {

    /**
     * Sets the inherited <code>length</code> variable and initializes the
     * <code>hit</code> array, based on the size of this ship (3 tiles).
     */
    public Cruiser(){
        this.length = 3;
        this.hit = new boolean[]{false, false, false};
        this.shipCoordinates = new ArrayList<>();
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
