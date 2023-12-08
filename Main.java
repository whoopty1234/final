import java.util.Scanner;
public class Main {

//main method that calls upon the other classes to play the game
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run){
            BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.play();

            System.out.println("Do you want to play again? (yes/no):");
            String playagaincheckeer = scan.next().toLowerCase();
            if (playagaincheckeer.equals("no")) {
                System.out.println("Thanks for playing. Goodbye!");
                break; // Exit the loop if the user does not want to play again
            } else if (!playagaincheckeer.equals("yes")) {
                System.out.println("Invalid response. Exiting.");
                break; // Exit the loop for invalid responses
            }
        }
        
    }
}