class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    

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
