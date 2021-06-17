package model.card;

import model.pawn.Pawn;

import javax.swing.*;

public class NumberSevenCard extends NumberCard {

    /**
     * Constructors: constructs a new instance of Number Seven Card
     * through the parent class NumberCard
     * value = 7
     */
    public NumberSevenCard() {
        super(7);
    }

    /**
     * Transformer: moves or exchanges the pawn
     * Post-condition: the pawn has either been moved or exchanged
     * @param pawn the pawn to be played
     */
    @Override
    public String chooseAction(Pawn pawn) {
        JFrame frame = new JFrame();
        String[] options = {"1", "2", "3", "4", "5", "6", "7"};
        String reply = (String) JOptionPane.showInputDialog(frame, "Select steps for "+pawn.getName()+".\nSelect 7 if you want to move only this Pawn.",
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
