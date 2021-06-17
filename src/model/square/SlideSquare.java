package model.square;


public class SlideSquare extends Square {
    String color;
    private boolean isEmpty;


    /**
     * Constructor: creates a new empty slide square
     * @param color the slide's color
     */
    SlideSquare(String color) {
        super(true, color);
        this.color = color;
        isEmpty = true;
    }

    /**
     * Accessor: gets the square's owner
     * Post-condition: the owner is returned
     * @return square's owner
     */
    /*public Player getOwner() {
        return this.owner;
    }*/

    /**
     * Accessor: gets the slide's color
     * Post-condition: the color is returned
     * @return the square's color
     */
    @Override
    public String getColor() {
        return color;
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
