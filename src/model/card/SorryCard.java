package model.card;

import model.pawn.Pawn;

import javax.swing.*;

public class SorryCard extends Card {
    private int value;

    /**
     * Constructor: constructs a new instance of the Sorry! Card
     //* @param friendly the friendly pawn
     //* @param hostile the target (enemy's) pawn
     */
    public SorryCard() {
        this.value = -1;
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
                "Playing Sorry Card ", JOptionPane.QUESTION_MESSAGE, null,
                options,options[0]);
        return reply;
    }

    /**
     * Transformer: moves the pawn
     * Post-condition: the pawn has been moved
     * @param pawn is the player's own pawn
     */
    @Override
    public void movePawn(Pawn pawn, int position) {
        if (pawn.getSquare() != position) {
            pawn.setPosition(position);
        }
    }

}
