import java.util.ArrayList;

public class Destroyer extends Ship {

    /**
     * Sets the inherited <code>length</code> variable and initializes the
     * <code>hit</code> array, based on the size of this ship (2 tiles).
     */
    public Destroyer(){
        this.length = 2;
        this.hit = new boolean[]{false, false};

    }

    /**
     * Specified by <code>getShipType</code> in class <code>Ship</code>>
     *
     * @return "Destroyer", indicating that this is a Destroyer.
     */
    @Override
    public String getShipType() {
        return "Destroyer";
    }
}
