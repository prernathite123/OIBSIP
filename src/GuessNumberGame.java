
    import java.util.Scanner;

    class GuessNumberGame
    {
        //Method for guessing the number enter by user
        public static void
        guessingNumberGame()
        {
            // Scanner Class for taking the input from user
            Scanner sc = new Scanner(System.in);

            // Generate the numbers
            int number = 1 + (int)(100* Math.random());

            // Given K trials
            int K = 10;

            int i, guess;

            System.out.println(
                    "A number is chosen between 1 to 100 Guess the number within 5 trials.");
            for(int j=1;j<10;j++)
            {


                // By using loop k trials are created
                for (i = 0; i < K; i++) {

                    System.out.println(  "Guess the number:");

                    // Take input for guessing
                    guess = sc.nextInt();

                    // If the number is guessed
                    if (number == guess) {
                        System.out.println(
                                "Congratulations!"
                                        + " You guessed the number.");
                        System.out.println("your Score ::::"+j);
                        break;
                    }
                    else if (number > guess
                            && i != K - 1) {
                        System.out.println(
                                "The number is "
                                        + "greater than " + guess);
                    }
                    else if (number < guess
                            && i != K - 1) {
                        System.out.println(
                                "The number is"
                                        + " less than " + guess);
                    }
                }

                if (i == K) {
                    System.out.println(
                            "GAME OVER!!!!!!");

                    System.out.println(
                            "The number was " + number);
                }
            }
        }
        public static void main(String args[])
        {
            guessingNumberGame();
        }
    }

