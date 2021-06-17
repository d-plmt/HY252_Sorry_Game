package model.card;

import model.pawn.Pawn;


/**
 *  Abstract class for creating a new
 *  Number or Sorry Card.
 * @version 1.0
 * @author Dafni Palimetaki
 */
abstract public class Card {
    private int value;

    /**
     * Transformer: lets the user choose which action to perform, or automatically chooses the default action of the card
     * Post-condition: the action is returned
     * @param pawn the pawn to be played
     * @return action
     */
    public String chooseAction(Pawn pawn) {
        return null;
    }

    /**
     * Accessor: checks if the card can be played for a specific pawn
     * Post-condition: a boolean is returned
     * @param pawn  the pawn to be played
     * @return  true if it can be played, else false
     */
    public boolean canBePlayed(Pawn pawn) {return true;}

    /**
     * Accessor: gets the value of the card
     * Post-condition: the value of the card is returned
     * @return value
     */
    public int getValue(){
        return value;
    }

    /**
     * Transformer: moves the pawn
     * Post-condition: the pawn has been moved
     * @param pawn is the player's own pawn
     */
    public void movePawn(Pawn pawn, int position) {    }

}
