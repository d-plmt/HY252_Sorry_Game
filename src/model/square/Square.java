package model.square;

public class Square {
    private boolean isEmpty;
    private String color;

    /**
     * Constructor: constructs a new instance of the class Square
     * @param isEmpty boolean, checks if the square has any pawn on it
     * @param color String, the color of the square
     */
    public Square(boolean isEmpty, String color) {
        this.isEmpty = isEmpty;
        this.color = color;
    }

    /**
     * Transformer: sets the isEmpty boolean
     * Post-condition: the square is either empty (true) or has a pawn on it (false)
     * @param isEmpty   boolean
     */
    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * Accessor: Returns whether the square is empty or not
     * Post-condition: Returns true if the square is empty, otherwise false
     * @return  boolean
     */
    public boolean getEmpty() {
        return this.isEmpty;
    }

    /**
     * Transformer: Sets the square's color
     * Post-condition: the square's color has been set
     * @param color String
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Accessor: returns the color of the square
     * post-condition: the color of the square is returned
     * @return  String
     */
    public String getColor() {
        return this.color;
    }
}
