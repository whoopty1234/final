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
    /**
     * Plays a game of Blackjack
     * players are given the option to hit or stand to determine the game.
     */
    public void play() {
        displayHands(false);

        while (calculateHandValue(playerHand) < 21) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to hit or stand? (h/s): ");
            char choice = scanner.next().charAt(0);

            if (choice == 'h') {
                playerHand.add(deck.drawCard());
                displayHands(false);
            } else if (choice == 's') {
                break;
            }
        }

        while (calculateHandValue(dealerHand) < 17) {
            dealerHand.add(deck.drawCard());
        }

        displayHands(true);
        displayResult();
    }
    /**
     * Calculates the value of a hand in Blackjack.
     * @param hand The hand to calculate the value for.
     * @return The total value of the hand.
     */
    private int calculateHandValue(List<Card> hand) {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            value += card.getValue();

            if (card.getRank().equals("A")) {
                numAces++;
            }
        }

        // Handles multiple aces
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }
    /**
     * Displays the current hands of the player and the dealer.
     * @param revealDealerCard Whether to show the dealer's full hand or only the first card
     */
    private void displayHands(boolean revealDealerCard) {
        System.out.println("Your hand: " + playerHand + " (Value: " + calculateHandValue(playerHand) + ")");
        System.out.println("Dealer's hand: " + (revealDealerCard ? dealerHand : dealerHand.subList(0, 1)));
    }
    /**
     * Displays the result of the game based on the final hands of the player and the dealer
     */
    private void displayResult() {
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);

        if (playerValue > 21) {
            System.out.println("You busted! Dealer wins.");
        } else if (dealerValue > 21) {
            System.out.println("Dealer busted! You win.");
        } else if (playerValue > dealerValue) {
            System.out.println("You win!");
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }
}