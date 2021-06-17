package model.deck;

import model.card.*;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    /**
     * Constructor: create a new instance of the class Deck
     * Create a new list of all the cards in the deck
     */
    public Deck() {
        cards = new ArrayList<Card>();
    }

    /**
     * Transformer: get a new card from the deck
     * post-condition: a new card has been taken
     * @param x number of the card
     * @return returns a new card
     */
    public Card getNewCard(int x) {
        return cards.get(x);
    }

    /**
     * Transformer: initialize all the cards in the game
     * Post-condition: the cards have been initialized
     */
    public void initializeCards() {
        for (int i=1; i<=4;i++) {
            for (int j=1;j<=12;j++) {
                switch (j) {
                    case 1:
                        cards.add(new NumberOneCard());
                        break;
                    case 2:
                        cards.add(new NumberTwoCard());
                        break;
                    case 4:
                        cards.add(new NumberFourCard());
                        break;
                    case 7:
                        cards.add(new NumberSevenCard());
                        break;
                    case 10:
                        cards.add(new NumberTenCard());
                        break;
                    case 11:
                        cards.add(new NumberElevenCard());
                        break;
                    case 6:
                    case 9:
                        break;
                    default:
                        cards.add(new SimpleNumberCard(j));
                }
            }
            cards.add(new SorryCard());
        }
        Collections.shuffle(cards);
    }

    /**
     * Transformer: after playing, remove a card from the deck
     * Post-condition: the card has been removed
     * @param card Card
     */
    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    /**
     * Accessor: checks if there are any remaining cards in the deck
     * Post-condition: returns true if there are NO more cards, otherwise false
     * @return boolean
     */
    public boolean checkRemaining() {
        return cards.isEmpty();
    }


    /**
     * Transformer: adds a new card to the list
     * Post-condition: a new card has been added to the list
     * @param card the new card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Accessor: returns the card's value in position x
     * Post-condition: the card's value in x is returned
     * @param x position
     * @return the card's value
     */
    public int getValue(int x) {
        return cards.get(x).getValue();
    }

    /**
     * Accessor: returns the size of the cards list
     * Post-condition: the list size has been returned
     * @return  the list size
     */
    public int size() {
        return cards.size();
    }

    /**
     * Transformer: deletes all the cards from the deck
     * Post-condition: the deck has been cleared
     */
    public void deleteAll() {
        cards.clear();
    }

    /**
     * Accessor: returns all the cards in the list
     * Post-condition: the whole list has been returned
     * @return  the list
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

}
