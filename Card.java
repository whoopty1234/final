class Card {
    private String suit;
    private String rank;

    /**
     * Constructor for creating a new Card object.
     * @param suit The suit of the card (e.g., "Hearts", "Diamonds", "Clubs", "Spades").
     * @param rank The rank of the card (e.g., "2", "3", ..., "10", "J", "Q", "K", "A").
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the suit of this card
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }
    /**
     * Gets the rank of this card
     * @return The rank of the card
     */
    public String getRank() {
        return rank;
    }
    /**
     * Provides a string representation of the card, combining its rank and suit.
     * @return A string in the format "Rank of Suit", e.g., "Ace of Spades".
     */
    public String toString() {
        return rank + " of " + suit;
    }

    
    /**
     * Calculates and returns the value of the card according to the rules
     * @return The value of the card for Blackjack
     */
    public int getValue() {
        switch (rank) {
            case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9":
                return Integer.parseInt(rank);
            case "10": case "J": case "Q": case "K":
                return 10;
            case "A":
                return 11;  
            default:
                return 0;  // Invalid card
        }
    }
}
