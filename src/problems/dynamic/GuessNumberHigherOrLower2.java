package problems.dynamic;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 */
public class GuessNumberHigherOrLower2 {
    public static void main(String[] args) {
        System.out.println(getMoneyAmount(5));
    }

    private static int getMoneyAmount(int n) {
        // starting from middle is not always optimal, we will have check all possible combinations
        // if  n is 1 2 3 4 5, target is 5 -> we pick middle 3, then 4 (cost 7), but if we pick 2 then 4 cost is 6

        // for each number x in range [i, j], we calculate result when we pick x
        // result_when_pick_x = x + Math.max(result(i, x-1), result(x+1, j));
        // and calculate min of all the maxes, because we want to minimum our cost from all possible worst cases

        int[][] dp = new int[n+1][n+1]; // dp[i][j] = min cost in worst case from i to j
        minimax(1, n, dp);
        return dp[1][n]; // min cost in worst case from 1 to n
    }

    // O(n^3)
    // i * j * k
    private static int minimax(int i, int j, int[][] dp) {
        if (i >= j) {
            return 0;
        }

        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            // divide our problem space
            int temp = k + Math.max(minimax(i, k-1, dp), minimax(k+1, j, dp)); // find max worst case
            ans = Math.min(ans, temp);
        }
        dp[i][j] = ans; // we are interested in minimizing worst case
        return dp[i][j];
    }
}
