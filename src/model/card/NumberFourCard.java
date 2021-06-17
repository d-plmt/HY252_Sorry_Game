package model.card;

import model.pawn.Pawn;

public class NumberFourCard extends NumberCard {

    /**
     * Constructor: creates a new instance of Number Four Card
     * through the parent class NumberCard.
     * number = 4
     */
    public NumberFourCard() {
        super(4);
    }

    /**
     * Transformer: chooses the action for the pawn
     * Post-condition: the default action is returned
     * @param pawn the pawn to be played
     */
    @Override
    public String chooseAction(Pawn pawn) {
        return "Move Backwards 4";
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

    /**
     *Accessor: checks if the pawn can be played
     * @param pawn the player's pawn
     * @return boolean
     */
    @Override
    public boolean canBePlayed(Pawn pawn) {
        return pawn.getSquare() >= 0;
    }
}
