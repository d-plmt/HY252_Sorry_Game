package model.square;

public class SimpleSquare extends Square{

    /**
     * Constructor: constructs a new instance of the SimpleSquare class
     * @param isEmpty boolean, checks if the square is empty
     */
    public SimpleSquare(boolean isEmpty) {
        super(isEmpty, "white");
    }

    /**
     * Accessor: returns the color of the square
     * Post-condition: the color of the square is returned
     * @return  String
     */
    @Override
    public String getColor() {
        return "white";
    }
}
