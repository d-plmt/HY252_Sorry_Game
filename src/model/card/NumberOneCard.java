package model.card;


import model.pawn.Pawn;
import javax.swing.*;

public class NumberOneCard extends NumberCard {

    /**
     * Constructor: Creates a new instance of the Number One Card
     * through the parent NumberCard.
     * number = 1
     */
    public NumberOneCard() {
        super(1);
    }

    /**
     *Accessor: checks if the pawn can be played
     * @param pawn the player's pawn
     * @return boolean
     */
    public boolean canBePlayed(Pawn pawn) {
        return pawn.isStillPlaying();
    }

    /**
     * Transformer: moves the pawn, or puts it in the start.
     * Post-condition: the pawn is moved 1 step or put in the start
     * @param pawn
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
