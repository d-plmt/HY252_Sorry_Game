package model.square;


import model.pawn.Pawn;

public class SafetyZoneSquare extends Square {
    private String color;
    private boolean isEmpty;

    /**
     * Constructor: constructs a new instance of the SafetyZoneSquare class
     * @param isEmpty boolean, checks if the square is empty
     * @param ownercolor the owner's color
     */
    public SafetyZoneSquare(boolean isEmpty, String ownercolor) {
        super(isEmpty,ownercolor);
        this.color = ownercolor;
        this.isEmpty = isEmpty;
    }

    /**
     * Accessor: checks if the pawn can enter the safety zone
     * Post-condition: returns true if the Pawn is the correct color, otherwise false
     * @return  boolean
     */
    public boolean checkColors(Pawn pawn) {
        return pawn.getColor().equals(color);
    }
}
