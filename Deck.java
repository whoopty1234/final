import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a deck of playing cards
 */
class Deck {
    private List<Card> cards;

    /**
     * Constructs a new deck of playing cards
     * 52 playing cards shuffled
     */
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }

        shuffle();
    }
    /**
     * Shuffles the deck of cards
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
    /**
     * Draws a card from the top of the deck
     * @return The top card of the deck
     */
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            return null;  // Deck is empty
        }
    }
}
/**
 * The game of Blackjack!
 */
class BlackjackGame {
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    /**
     * Constructs a new game of Blackjack
     * deals to the player and dealer.
     */
    public BlackjackGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        dealInitialCards();
    }
    /**
     * Deals two cards to both the player and the dealer
     */
    private void dealInitialCards() {
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }
}