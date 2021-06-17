package model.card;

import model.pawn.Pawn;

import javax.swing.*;

/**
 * The class NumberCard creates a new type of card with numbers
 * @version 1.0
 * @author Dafni Palimetaki
 */

public class NumberCard extends Card{
    private int value;

    /**
     * Constructor: Constructs a new Number Card
     * with the given number and way of movement.
     * Post-condition: Creates and initializes the new card
     * with the given number and way of movement. Also initializes the steps
     * that the pawn will take.
     * @param value is the number on the card
     */
    public NumberCard(int value) {
        this.value = value;
    }

    /**
     * Accessor: Returns the card's number
     * Post-condition: Returns the card's number
     * @return the card's value
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
        JFrame frame = new JFrame();
        String[] options = {"Place Pawn at the start", "Move Pawn"};
        String reply = (String) JOptionPane.showInputDialog(frame, "Choose which pawn to play:",
                "Playing Card "+this.getValue(), JOptionPane.QUESTION_MESSAGE, null,
                options,options[0]);
        return reply;
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
