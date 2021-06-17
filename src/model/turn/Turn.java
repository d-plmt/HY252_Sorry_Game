package model.turn;

import model.player.Player;

public class Turn {

    private Player current;

    /**
     * Constructor: creates a new instance of Turn with Player "current"
     */
    public Turn(Player current) {
        this.current = current;
    }

    /**
     * Transformer: Sets the player's turn.
     * Post-condition: the player's turn has been set
     * @param current the player to play
     */
    public void setTurn(Player current) {
        this.current = current;
    }

    /**
     * Accessor: gets the player's turn.
     * Post-condition: the player's turn has been returned.
     * @return the turn
     */
    public Player getTurn() {
        return this.current;
    }
}
