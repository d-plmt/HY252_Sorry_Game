package model.pawn;

import model.player.Player;
import model.square.Square;

public class Pawn {
    private Player player;
    private Square square;
    private String color;
    private String name;
    private boolean stillPlaying;
    private int position;

    /**
     * Constructor: create a new instance of the class Pawn
     * with owner (player), starting square and color
     * @param player    the owner
     * @param square    starting point
     */
    public Pawn(Player player, Square square, String name) {
        this.player = player;
        this.square = square;
        this.color = player.getColor();
        this.stillPlaying = true;
        this.position = -1;
        this.name = name;
    }

    /**
     * Accessor: returns the pawn's color
     * Post-condition: the pawn's color is returned
     * @return the name of the color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Accessor: returns the pawn's name (Pawn 1, Pawn 2)
     * Post-condition: the pawn's name has been returned
     * @return the name of the pawn
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor: returns the player
     * Post-condition: the player has been returned
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Transformer: sets the pawn's position
     * Post-condition: the position has been set
     * @param square the position
     */
    public void setPosition(int square) {
        this.position = square;
    }

    /**
     * Accessor: returns the position
     * Post-condition: the position has been returned
     * @return the position
     */
    public int getSquare() {
        return this.position;
    }

    /**
     * Transformer: sets the variable stillPlaying to false if the pawn has been removed
     * Post-condition: stillPlaying is false, if it has been removed
     */
    public void stopPlaying() {
        this.stillPlaying = false;
    }

    /**
     * Observer: checks if the pawn is still playing
     * Post-condition: will return true if the pawn is still playing or false if it has been removed
     * @return true if pawn is playing, else false
     */
    public boolean isStillPlaying() {
        return this.stillPlaying;
    }
}
