package controller;

import model.card.Card;
import model.deck.Deck;
import model.pawn.Pawn;
import model.player.Player;
import model.square.*;
import model.turn.Turn;
import view.GraphicUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Controller {

    private GraphicUI GUI;
    public Player player1, player2;
    private Card current;
    public Deck cards;
    private Deck played;
    private boolean gamestart;
    public Turn turn;
    private Square[] path, safety1, safety2;
    public Square home1, home2, start1, start2;
    private Pawn p1pawn1, p1pawn2, p2pawn1, p2pawn2;
    private int random;

    /**
     * Constructor: creates a new instance of the Controller class
     * with 2 players, pawns, squares and cards
     */
    public Controller() {
        player1 = new Player("Player 1", "Red");
        player2 = new Player("Player 2", "Yellow");
        cards = new Deck();
        cards.initializeCards();
        played = new Deck();
        path = new Square[60];
        safety1 = new SafetyZoneSquare[5];
        safety2 = new SafetyZoneSquare[5];
        init_squares();
        init_pawns();
        init_turn();
        GUI = new GraphicUI();
        GUI.updateinfo(this);
        GUI.getCardsButton().addMouseListener(new CardListener());
        GUI.getFoldButton().addMouseListener(new FoldListener());
        GUI.initp1pawn1();
        GUI.initp1pawn2();
        GUI.initp2pawn1();
        GUI.initp2pawn2();
    }

    /**
     *transformer: initializes all the squares
     * post-condition: the squares have been initialized
     */
    private void init_squares() {
        for (int i = 0; i<60;i++) {
            if (i<14) {
                if ((i == 1) || (i == 9)) {
                    path[i] = new StartSlideSquare("red");
                } else if ((i == 2) || (i == 3) || ((i > 9) && (i < 13))) {
                    path[i] = new InternalSlideSquare("red");
                } else if ((i == 4) || (i == 13)) {
                    path[i] = new EndSlideSquare("red");
                }
                else {
                    path[i] = new SimpleSquare(true);
                }
            }
            else if (i<29) {
                if ((i==16) || (i==24)) {
                    path[i] = new StartSlideSquare("blue");
                }
                else if ((i==17) || (i==18) || ((i>24) && (i<28))) {
                    path[i] = new InternalSlideSquare("blue");
                }
                else if ((i==19) || (i==28)) {
                    path[i] = new EndSlideSquare("blue");
                }
                else {
                    path[i] = new SimpleSquare(true);
                }
            }
            else if (i<44) {
                if ((i==31) || (i==39)) {
                    path[i] = new StartSlideSquare("yellow");
                }
                else if ((i==32) || (i==33) || ((i>39) && (i<43))) {
                    path[i] = new InternalSlideSquare("yellow");
                }
                else if ((i==34) || (i==43)) {
                    path[i] = new EndSlideSquare("yellow");
                }
                else {
                    path[i] = new SimpleSquare(true);
                }
            }
            else {
                if ((i==46) || (i==54)) {
                    path[i] = new StartSlideSquare("green");
                }
                else if ((i==47) || (i==48) || ((i>54) && (i<58))) {
                    path[i] = new InternalSlideSquare("green");
                }
                else if ((i==49) || (i==58)) {
                    path[i] = new EndSlideSquare("green");
                }
                else {
                    path[i] = new SimpleSquare(true);
                }
            }
        }
        for (int i = 0; i<5; i++) {
            safety1[i] = new SafetyZoneSquare(true,"red");
            safety2[i] = new SafetyZoneSquare(true, "yellow");
        }
        home1 = new HomeSquare(true, "red");
        home2 = new HomeSquare(true, "yellow");
        start1 = new StartSquare(true, "red");
        start2 = new StartSquare(true, "yellow");
    }

    /**
     * transformer: initializes the 4 pawns
     * post-condition: the pawns have been initialized
     */
    private void init_pawns() {
        p1pawn1 = new Pawn(player1, null, "Pawn 1");
        p1pawn2 = new Pawn(player1, null, "Pawn 2");
        p2pawn1 = new Pawn(player2, null, "Pawn 1");
        p2pawn2 = new Pawn(player2, null, "Pawn 2");
    }

    /**
     * transformer: randomly picks which player will play 1st
     * post-condition: the 1st turn has been picked
     */
    private void init_turn() {
        random = 1 + (int) (Math.random() * 2);
        if (random==1) {
            turn = new Turn(player1);
        }
        else {
            turn = new Turn(player2);
        }
    }

    /**
     * accessor: gets the current player's turn
     * @return the current turn
     */
    public Player getCurrentTurn() {
        return turn.getTurn();
    }

    /**
     *transformer: sets the next turn
     * post-condition: the next player has to play now
     */
    public void setNewTurn() {
        if (turn.getTurn()==player1) {
            turn.setTurn(player2);
        }
        else {
            turn.setTurn(player1);
        }
        GUI.updateinfo(this);
    }

    /**
     * Transformer: plays a card from the list of cards
     * Post-condition: a new card is played
     * @param index
     */
    public void CardPlay(int index) {

        JFrame frame = new JFrame();
        current = cards.getNewCard(index);
        GUI.last_value = current.getValue();
        GUI.updateopenCards();
        String reply = null;
        String[] options = {"Pawn 1", "Pawn 2"};
        if (getCurrentTurn()==player1) {
            reply = (String) JOptionPane.showInputDialog(frame, "Choose which pawn to play:",
                    "Playing Card "+current.getValue(), JOptionPane.QUESTION_MESSAGE, null,
                    options,options[0]);
            if ((current.getValue()==1) || (current.getValue()==2)){
                if (reply.equals("Pawn 1")) {
                    if (current.canBePlayed(p1pawn1)) {
                        if (current.chooseAction(p1pawn1).equals("Place Pawn at the start")) {
                            if (p1pawn1.getSquare() == -1) {            //-1 for start position
                                current.movePawn(p1pawn1, 0);
                                p1pawn1.setPosition(0);
                                GUI.updatePawn(p1pawn1);
                                GUI.repaint();
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"This Pawn is already playing.\nYou lose your turn.");
                            }
                        }
                        else {
                            if (p1pawn1.getSquare() != -1) {
                                current.movePawn(p1pawn1,p1pawn1.getSquare()+current.getValue());
                                GUI.updatePawn(p1pawn1);
                                GUI.repaint();
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")){
                    if (current.canBePlayed(p1pawn2)) {
                        if (current.chooseAction(p1pawn2).equals("Place Pawn at the start")) {
                            if (p1pawn2.getSquare() == -1) {
                                current.movePawn(p1pawn2, 0);
                                p1pawn2.setPosition(0);
                                GUI.updatePawn(p1pawn2);
                                GUI.repaint();
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"This Pawn is already playing.\nYou lose your turn.");
                            }
                        }
                        else {
                            if (p1pawn2.getSquare() != -1) {
                                current.movePawn(p1pawn2,p1pawn2.getSquare()+current.getValue());
                                GUI.updatePawn(p1pawn2);
                                GUI.repaint();
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
            else if ((current.getValue()==3) || (current.getValue()==5) || (current.getValue()==8) || (current.getValue()==12)) {
                if (reply.equals("Pawn 1")) {
                    if (p1pawn1.getSquare() != -1) {
                        current.movePawn(p1pawn1,p1pawn1.getSquare()+current.getValue());
                        GUI.updatePawn(p1pawn1);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")){
                    if (p1pawn2.getSquare() != -1) {
                        current.movePawn(p1pawn2,p1pawn2.getSquare()+current.getValue());
                        GUI.updatePawn(p1pawn2);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
            else if (current.getValue()==4) {
                if (reply.equals("Pawn 1")) {
                    if (p1pawn1.getSquare()!=-1) {
                        if (p1pawn1.getSquare() <4) {
                            current.movePawn(p1pawn1,56+p1pawn1.getSquare());
                        }
                        else {
                            current.movePawn(p1pawn1,p1pawn1.getSquare()-4);
                        }
                        GUI.updatePawn(p1pawn1);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")) {
                    if (p1pawn2.getSquare()!=-1) {
                        if (p1pawn2.getSquare() <4) {
                            current.movePawn(p1pawn2,56+p1pawn2.getSquare());
                        }
                        else {
                            current.movePawn(p1pawn2,p1pawn2.getSquare()-4);
                        }
                        GUI.updatePawn(p1pawn2);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
            else if (current.getValue()==10) {
                if (reply.equals("Pawn 1")) {
                    if (p1pawn1.getSquare() != -1) {
                        if (current.chooseAction(p1pawn1).equals("Move forward")) {
                            if (p1pawn1.getSquare()>49) {
                                current.movePawn(p1pawn1, p1pawn1.getSquare()-50);
                            }
                            else {
                                current.movePawn(p1pawn1, p1pawn1.getSquare()+10);
                            }
                        }
                        else {
                            System.out.println("HMM");
                            if (p1pawn1.getSquare()==0) {
                                current.movePawn(p1pawn1,59);
                            }
                            else {
                                current.movePawn(p1pawn1, p1pawn1.getSquare()-1);
                            }
                        }
                        GUI.updatePawn(p1pawn1);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")) {
                    if (p1pawn2.getSquare() != -1) {
                        if (current.chooseAction(p1pawn2).equals("Move forward")) {
                            if (p1pawn2.getSquare()>49) {
                                current.movePawn(p1pawn2, p1pawn2.getSquare()-50);
                            }
                            else {
                                current.movePawn(p1pawn2, p1pawn2.getSquare()+10);
                            }
                        }
                        else {
                            if (p1pawn2.getSquare()==0) {
                                current.movePawn(p1pawn2,59);
                            }
                            else {
                                current.movePawn(p1pawn2, p1pawn2.getSquare()-1);
                            }
                        }
                        GUI.updatePawn(p1pawn2);
                        GUI.repaint();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                }
            }
            else if (current.getValue()==7) {
                if (reply.equals("Pawn 1")) {
                    if (p1pawn1.getSquare() != -1) {
                        int steps = Integer.parseInt(current.chooseAction(p1pawn1));
                        current.movePawn(p1pawn1,p1pawn1.getSquare()+steps);
                        GUI.updatePawn(p1pawn1);
                        if (7-steps>0) {
                            if (p1pawn2.getSquare() != -1) {
                                current.movePawn(p1pawn2,p1pawn2.getSquare()+7-steps);
                                GUI.updatePawn(p1pawn2);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "The other Pawn can't be played.\nYou lose the extra steps.");
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")){
                    if (p1pawn2.getSquare() != -1) {
                        int steps = Integer.parseInt(current.chooseAction(p1pawn2));
                        current.movePawn(p1pawn2,p1pawn2.getSquare()+steps);
                        GUI.updatePawn(p1pawn2);
                        if (7-steps>0) {
                            if (p1pawn1.getSquare() != -1) {
                                current.movePawn(p1pawn1,p1pawn1.getSquare()+7-steps);
                                GUI.updatePawn(p1pawn1);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "The other Pawn can't be played.\nYou lose the extra steps.");
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
        }
        else if (getCurrentTurn()==player2) {
            reply = (String) JOptionPane.showInputDialog(frame, "Choose which pawn to play:",
                    "Playing Card "+current.getValue(), JOptionPane.QUESTION_MESSAGE, null,
                    options,options[0]);
            if ((current.getValue()==1) || (current.getValue()==2)){
                if (reply.equals("Pawn 1")) {
                    if (current.canBePlayed(p2pawn1)) {
                        if (current.chooseAction(p2pawn1).equals("Place Pawn at the start")) {
                            if (p2pawn1.getSquare() == -1) {                //-1 for "start" position
                                current.movePawn(p2pawn1, 0);
                                p2pawn1.setPosition(0);
                                GUI.updatePawn(p2pawn1);
                                GUI.repaint();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "This Pawn is already playing.\nYou lose your turn.");
                            }
                        }
                        else {
                            if (p2pawn1.getSquare() != -1) {
                                current.movePawn(p2pawn1,p2pawn1.getSquare()+current.getValue());
                                GUI.updatePawn(p2pawn1);
                                GUI.repaint();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                            }
                        }
                    }
                }
                else if (reply.equals("Pawn 2")){
                    if (current.canBePlayed(p2pawn2)) {
                        if (current.chooseAction(p2pawn2).equals("Place Pawn at the start")) {
                            if (p2pawn2.getSquare() == -1) {
                                current.movePawn(p2pawn2, 0);
                                p2pawn2.setPosition(0);
                                GUI.updatePawn(p2pawn2);
                                GUI.repaint();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "This Pawn is already playing.\nYou lose your turn.");
                            }
                        }
                        else {
                            if (p2pawn2.getSquare() != -1) {
                                current.movePawn(p2pawn2,p2pawn2.getSquare()+current.getValue());
                                GUI.updatePawn(p2pawn2);
                                GUI.repaint();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                            }
                        }
                    }
                }
            }
            else if ((current.getValue()==3) || (current.getValue()==5) || (current.getValue()==8) || (current.getValue()==12)) {
                if (reply.equals("Pawn 1")) {
                    if (p2pawn1.getSquare() != -1) {
                        current.movePawn(p2pawn1,p2pawn1.getSquare()+current.getValue());
                        GUI.updatePawn(p2pawn1);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")){
                    if (p2pawn2.getSquare() != -1) {
                        current.movePawn(p2pawn2,p2pawn2.getSquare()+current.getValue());
                        GUI.updatePawn(p2pawn2);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
            else if (current.getValue()==4) {
                if (reply.equals("Pawn 1")) {
                    if (p2pawn1.getSquare()!=-1) {
                        if (p2pawn1.getSquare() <4) {
                            current.movePawn(p2pawn1,56+p2pawn1.getSquare());
                        }
                        else {
                            current.movePawn(p2pawn1,p2pawn1.getSquare()-4);
                        }
                        GUI.updatePawn(p2pawn1);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")) {
                    if (p2pawn2.getSquare()!=-1) {
                        if (p2pawn2.getSquare() <4) {
                            current.movePawn(p2pawn2,56+p2pawn2.getSquare());
                        }
                        else {
                            current.movePawn(p2pawn2,p2pawn2.getSquare()-4);
                        }
                        GUI.updatePawn(p2pawn2);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
            else if (current.getValue()==10) {
                if (reply.equals("Pawn 1")) {
                    if (p2pawn1.getSquare() != -1) {
                        if (current.chooseAction(p2pawn1).equals("Move forward")) {
                            if (p2pawn1.getSquare()>49) {
                                current.movePawn(p2pawn1, p2pawn1.getSquare()-50);
                            }
                            else {
                                current.movePawn(p2pawn1, p2pawn1.getSquare()+10);
                            }
                        }
                        else {
                            System.out.println("HMMM");
                            if (p2pawn1.getSquare()==0) {
                                current.movePawn(p2pawn1,59);
                            }
                            else {
                                current.movePawn(p2pawn1, p2pawn1.getSquare()-1);
                            }
                        }
                        GUI.updatePawn(p2pawn1);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")) {
                    if (p2pawn2.getSquare() != -1) {
                        if (current.chooseAction(p2pawn2).equals("Move forward")) {
                            if (p2pawn2.getSquare()>49) {
                                current.movePawn(p2pawn2, p2pawn2.getSquare()-50);
                            }
                            else {
                                current.movePawn(p2pawn2, p2pawn2.getSquare()+10);
                            }
                        }
                        else {
                            if (p2pawn2.getSquare()==0) {
                                current.movePawn(p2pawn2,59);
                            }
                            else {
                                current.movePawn(p2pawn2, p2pawn2.getSquare()-1);
                            }
                        }
                        GUI.updatePawn(p2pawn2);
                        GUI.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
            else if (current.getValue()==7) {
                if (reply.equals("Pawn 1")) {
                    if (p2pawn1.getSquare() != -1) {
                        int steps = Integer.parseInt(current.chooseAction(p2pawn1));
                        current.movePawn(p2pawn1,p2pawn1.getSquare()+steps);
                        GUI.updatePawn(p2pawn1);
                        if (7-steps>0) {
                            if (p2pawn2.getSquare() != -1) {
                                current.movePawn(p2pawn2,p2pawn2.getSquare()+7-steps);
                                GUI.updatePawn(p2pawn2);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "The other Pawn can't be played.\nYou lose the extra steps.");
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
                else if (reply.equals("Pawn 2")){
                    if (p2pawn2.getSquare() != -1) {
                        int steps = Integer.parseInt(current.chooseAction(p2pawn2));
                        current.movePawn(p2pawn2,p2pawn2.getSquare()+steps);
                        GUI.updatePawn(p2pawn2);
                        if (7-steps>0) {
                            if (p2pawn1.getSquare() != -1) {
                                current.movePawn(p2pawn1,p2pawn1.getSquare()+7-steps);
                                GUI.updatePawn(p2pawn1);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "The other Pawn can't be played.\nYou lose the extra steps.");
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This Pawn can't be played.\nYou lose your turn.");
                    }
                }
            }
        }
        if (reply == null) {
            return;
        }
        played.addCard(current);
        cards.removeCard(current);
        if (cards.size()==1) {
            GUI.last_value=-1;
            cards.initializeCards();
            played.deleteAll();
            GUI.updateinfo(this);
            GUI.updateopenCards();
            GUI.repaint();
        }
        setNewTurn();
    }

    /**
     * Transformer: creates a listener for the fold button
     * Post-condition: a listener has been created
     */
    private class FoldListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                played.addCard(current);
                cards.removeCard(current);
                setNewTurn();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


    /**
     * Transformer: creates a listener for the card button
     * Post-condition: a listener has been created
     */
    private class CardListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (gameOver() || (cards.size()==0)) {
                return;
            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                CardPlay(1);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * Transformer: sets the value of gamestart
     * Post-condition: gamestart is set
     * @param gamestart boolean
     */
    public void setGamestart(boolean gamestart) {
        this.gamestart = gamestart;
    }

    /**
     * Accessor: returns true if the game can start, else false
     * Post-condition: the game can either start(true) or not (false)
     * @return boolean
     */
    public boolean getGameStart() {
        return this.gamestart;
    }

    /**
     * Transformer: returns if there is a winner or not
     * Post-condition: returns the winner
     * @return  boolean
     */
    public void winner() {}

    /**
     * Transformer: ends the game
     * Post-condition: the game has ended
     */
    public boolean gameOver() {
        return false;
    }

}
