package model.card;

import model.pawn.Pawn;

import javax.swing.*;

public class NumberTwoCard extends NumberCard {

    /**
     * Constructor: Creates a new instance of the Number One Card
     * through the parent NumberCard.
     * number = 2
     */
    public NumberTwoCard() {
        super(2);
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
        String reply = (String) JOptionPane.showInputDialog(frame, "Choose Action:",
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
