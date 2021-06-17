package model.square;


public class HomeSquare extends Square {
    private String color;
    private boolean isEmpty;

    /**
     * Constructor: creates a new empty Home Square with owner
     * @param ownercolor the owner's color
     */
    public HomeSquare(boolean isEmpty, String ownercolor) {
        super(isEmpty, ownercolor);
        this.color = ownercolor;
        this.isEmpty = isEmpty;
    }


    /**
     * Accessor: gets the square's (owner's) color
     * Post-condition: the color is returned
     * @return the square's color
     */
    @Override
    public String getColor() {
        return this.color;
    }

    /**
     * Transformer: changes the square's status to empty (true) or not empty (false)
     * Post-condition: the square's status is changed
     * @param isEmpty   true if empty, else false
     */
    @Override
    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * Accessor: gets the square's status
     * Post-condition: the square's status is true if empty, else false
     * @return true for empty, else false
     */
    @Override
    public boolean getEmpty() {
        return this.isEmpty;
    }
}
