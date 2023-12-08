import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
//class representing the logic of a simple Blackjack game
class BlackjackGame {
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
                displayHands(false); // display hands after hti
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
// method to display hands, optionally revealing the dealer's second hand
    private void displayHands(boolean revealDealerCard) {
        System.out.println("Your hand: " + playerHand + " (Value: " + calculateHandValue(playerHand) + ")");
        System.out.println("Dealer's hand: " + (revealDealerCard ? dealerHand : dealerHand.subList(0, 1)));
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


