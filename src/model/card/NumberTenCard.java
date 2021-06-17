package model.card;

import model.pawn.Pawn;

import javax.swing.*;

public class NumberTenCard extends NumberCard {

    /**
     *  Constructor: constructs a new instance of Number Ten Card
     *  through the parent class NumberCard.
     *  value = 10
     */
    public NumberTenCard() {
        super(10);
    }

    /**
     * Transformer: moves or exchanges the pawn
     * Post-condition: the pawn has either been moved or exchanged
     * @param pawn the pawn to be played
     */
    @Override
    public String chooseAction(Pawn pawn) {
        JFrame frame = new JFrame();
        String[] options = {"Move forward", "Move backwards"};
        String reply = (String) JOptionPane.showInputDialog(frame, "Choose Action:",
                "Playing Card "+this.getValue(), JOptionPane.QUESTION_MESSAGE, null,
                options,options[0]);
        System.out.println("1212"+reply+"12313");
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
