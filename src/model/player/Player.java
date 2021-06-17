package model.player;

public class Player {

    private String name;
    private String color;

    /**
     * Constructor: constructs a new instance of the class Player
     * with name, color, and 2 pawns
     * @param name  String, name of the player
     * @param color String, name of the player's color
     */
    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }


    /**
     * Accessor: returns the name of the player
     * Post-condition: the name of the player has been returned
     * @return  String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Transformer: sets the player's color
     * Post-condition: the player's color has been set
     * @param color String
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Accessor: returns the player's color
     * Post-condition: the player's color has been returned
     * @return String
     */
    public String getColor() {
        return this.color;
    }

}
