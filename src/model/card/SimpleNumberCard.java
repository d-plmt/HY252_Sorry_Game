package model.card;

import model.pawn.Pawn;

public class SimpleNumberCard extends NumberCard {
    private int value;

    /**
     * Constructor: constructs a new instance of Simple Number Card
     * through the parent class NumberCard
     * deksia = true
     * @param value int, number on the card
     */
    public SimpleNumberCard(int value) {
        super(value);
        this.value = value;
    }

    /**
     * Accessor: Returns the card's number
     * Post-condition: Returns the card's number
     * @return the card's number
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * Transformer: moves or exchanges the pawn
     * Post-condition: the pawn has either been moved or exchanged
     * @param pawn the pawn to be played
     */
    @Override
    public String chooseAction(Pawn pawn) {
        return "Move Pawn";
    }


    /**
     * Transformer: moves the pawn
     * Post-condition: the pawn has been moved
     * @param pawn is the player's own pawn
     * @param position the position to be moved to
     */
    @Override
    public void movePawn(Pawn pawn, int position) {
        pawn.setPosition(position);
    }

}
