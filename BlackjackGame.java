import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
//class representing the logic of a simple Blackjack game / colors player and dealers hand
class BlackjackGame {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m"; // Blue for the player's hand
    public static final String ANSI_GREEN = "\u001B[32m"; // Green for the dealer's hand
    private Deck deck; // deck of cards for the game
    private List<Card> playerHand; // player's hand
    private List<Card> dealerHand; // dealer's hand
    // this constructor initializes the deck and deals initial cards
    public BlackjackGame() {
        deck = new Deck(); // creates a new deck
        playerHand = new ArrayList<>(); // initiliazes an empty deck for the player
        dealerHand = new ArrayList<>(); // initializes an empty list for the dealers
        dealInitialCards(); // deal initial card to player and dealers
    }
// method to deal initial cards to both players and dealer
    private void dealInitialCards() {
        playerHand.add(deck.drawCard()); // draw a card for the player
        dealerHand.add(deck.drawCard()); // draw a card for dealer
        playerHand.add(deck.drawCard()); // draw another card for the player
        dealerHand.add(deck.drawCard()); // draw another card for the dealer
    }
    // method to start and control the game
    public void play() {
        displayHands(false); // displays initial hand without revealing the dealer's second card
        // gives players choice to hit or stand
        while (calculateHandValue(playerHand) < 21) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to hit or stand? (h/s): ");
            char choice = scanner.next().charAt(0);
            if (choice == 'h') {
                playerHand.add(deck.drawCard()); //player has choice to hit
                displayHands(false); // display hands after hit
            } else if (choice == 's') {
                break; // player chooses to stand
            }
        }
        //dealer draws cards until hand value is at least 17
        while (calculateHandValue(dealerHand) < 17) {
            dealerHand.add(deck.drawCard());
        }
        // display final hands with the dealer's cards and display results
        displayHands(true);
        displayResult();
    }
    // method to calculate the value of a hand

    /**
     * 
     * @param hand 
     * @return
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

        // Handle multiple aces
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }
// method to display hands, then revealing the dealer's second hand
    private void displayHands(boolean revealDealerCard) {
        System.out.println(ANSI_BLUE + "Your hand: " + playerHand + " (Value: " + calculateHandValue(playerHand) + ")" + ANSI_RESET);
        // Check if it's time to reveal the dealer's card and value
        if (revealDealerCard) {
            // Display the full dealer's hand and its value
            System.out.println(ANSI_GREEN + "Dealer's hand: " + dealerHand + " (Value: " + calculateHandValue(dealerHand) + ")" + ANSI_RESET);
        } else {
            // Display only the first card of the dealer's hand
            System.out.println(ANSI_GREEN + "Dealer's hand: " + dealerHand.subList(0, 1) + ANSI_RESET);
        }
    }
//method to display the results of the game
    private void displayResult() {
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);
//determine winner
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


