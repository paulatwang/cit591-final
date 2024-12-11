public class EmptySea extends Ship {

    /**
     * Sets the inherited <code>length</code> variable and initializes the
     * <code>hit</code> array, based on the size of this Empty Sea (1 tiles).
     */
    public EmptySea(){
        this.length = 1;
        this.hit = new boolean[]{false};
    }

    /**
     * Since an EmptySea is empty by definition, shooting at one will always be a miss.
     *
     * @param row the row of the shot
     * @param column the column of the shot
     * @return {@literal false} always, since nothing will be hit.
     */
    @Override
    public boolean shootAt(int row,
                           int column){
        return false;
    }

    /**
     * Since an EmptySea is empty by definition, it is not possible that one can be sunk.
     *
     * @return {@literal false} always, since nothing will be hit.
     */
    @Override
    public boolean isSunk(){
        return false;
    }

    /**
     * Returns a single character String to use in the Ocean's print method.
     * This method can only be used to print out locations in the ocean that
     * have been shot at; it should not be used to print locations that have
     * not been the target of a shot yet. Since an EmptySea is empty by definition,
     * targeting it will always result in a miss so return "-".
     *
     * @return "-" always, since nothing will be hit
     */
    @Override
    public String toString(){
        return "-";
    }


    /**
     * Specified by <code>getShipType</code> in class <code>Ship</code>>
     *
     * @return "EmptySea", indicating that this is an Empty sea tile.
     */
    @Override
    public String getShipType() {
        return "empty";
    }
}
