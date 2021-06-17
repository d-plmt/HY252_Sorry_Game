package model.square;

import model.pawn.Pawn;

public class StartSlideSquare extends SlideSquare {
    private String slideColor = super.color;

    public StartSlideSquare(String color) {
        super(color);

    }

    /**
     * Transformer: sets the color of the slide
     * Post-condition: the color of the slide is set
     * @param slideColor   String
     */
    public void setSlideColor(String slideColor) {
        this.slideColor = slideColor;
    }

    /**
     * Accessor: returns the color of the slide
     * Post-condition: the color of the slide is returned
     * @return  String
     */
    public String getSlideColor() {
        return this.slideColor;
    }

    /**
     * Accessor: checks if the pawn and the slide are the same color
     * Post-condition: true if the pawn and the slide are the same color, else false
     * @return  boolean
     */
    public boolean checkSameColors(Pawn pawn) {
        return slideColor.equals(pawn.getColor());
    }
}
