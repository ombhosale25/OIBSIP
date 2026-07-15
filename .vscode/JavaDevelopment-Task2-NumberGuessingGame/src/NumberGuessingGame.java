import java.util.*;

public class NumberGuessingGame {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean playAgain = true;
        int round = 1;

        ArrayList<String> scoreBoard = new ArrayList<>();

        System.out.println("=================================");
        System.out.println("     NUMBER GUESSING GAME");
        System.out.println("=================================");

        while (playAgain) {

            int range = 100;
            int maxAttempts = 7;

            System.out.println("\nChoose Difficulty");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    range = 50;
                    maxAttempts = 10;
                    break;

                case 2:
                    range = 100;
                    maxAttempts = 7;
                    break;

                case 3:
                    range = 200;
                    maxAttempts = 5;
                    break;

                default:
                    System.out.println("Invalid choice! Medium selected.");
                    range = 100;
                    maxAttempts = 7;
            }

            Random random = new Random();
            int number = random.nextInt(range) + 1;

            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nGuess the number between 1 and " + range);

            while (attempts < maxAttempts) {

                System.out.println("--------------------------------");

                System.out.println("Attempts Left : " + (maxAttempts - attempts));
                System.out.print("Enter your guess : ");

                int guess = sc.nextInt();

                attempts++;

                if (guess > number) {

                    System.out.println("Too High!");

                } else if (guess < number) {

                    System.out.println("Too Low!");

                } else {

                    System.out.println("Correct!");
                    System.out.println("You guessed it in " + attempts + " attempts.");

                    guessed = true;

                    scoreBoard.add("Round " + round + " - Guessed in " + attempts + " attempts");

                    break;
                }
            }

            if (!guessed) {

                System.out.println("\nYou Lost!");
                System.out.println("Correct Number was : " + number);

                scoreBoard.add("Round " + round + " - Lost");
            }

            System.out.println("\n========= SCOREBOARD =========");

            for (String s : scoreBoard) {
                System.out.println(s);
            }

            System.out.println("==============================");

            System.out.print("\nPlay Again? (yes/no): ");

            String ans = sc.next();

            if (!ans.equalsIgnoreCase("yes")) {
                playAgain = false;
            }

            round++;
        }

        System.out.println("\nThank you for playing!");
    }
}