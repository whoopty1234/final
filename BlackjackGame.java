import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
class BlackjackGame {
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        dealInitialCards();
    }

    private void dealInitialCards() {
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }

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

    private void displayHands(boolean revealDealerCard) {
        System.out.println("Your hand: " + playerHand + " (Value: " + calculateHandValue(playerHand) + ")");
        System.out.println("Dealer's hand: " + (revealDealerCard ? dealerHand : dealerHand.subList(0, 1)));
    }

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


