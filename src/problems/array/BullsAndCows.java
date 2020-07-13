package problems.array;

/**
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is.
 * Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both
 * digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows").
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 */
public class BullsAndCows {
    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        System.out.println(getHint(secret, guess));
    }

    // O(N), O(1)
    private static String getHint(String secret, String guess) {
        int[] secretArr = new int[10]; // 0 - 9 numbers
        int[] guessArr = new int[10];

        int bull = 0;
        int cow = 0;

        for (int i = 0; i < secret.length(); i++) {
            int num1 = secret.charAt(i) - '0';
            int num2 = guess.charAt(i) - '0';

            if (num1 == num2) {
                bull++;
            } else {
                secretArr[num1]++;
                guessArr[num2]++;
            }
        }

        for (int i = 0; i < 10; i++) { // note 10
            cow += Math.min(secretArr[i], guessArr[i]);
        }

        return bull + "A" + cow + "B";
    }
}
