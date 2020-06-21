package problems.dynamic;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 */
public class CoinChange2 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(change(5, coins));
    }

    // BOTTOM UP (space optimization)
    // O(n * amount), O(amount)
    private static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }

    // TOP DOWN
    // O(n * amount)
    private static int change2(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return cc(coins, 0, amount, dp);
    }

    private static int cc(int[] coins, int index, int amount, int[][] dp) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index == coins.length) {
            return 0;
        }
        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }

        return dp[index][amount] = cc(coins, index, amount - coins[index], dp) +
                cc(coins, index+1, amount, dp); // we can reuse same element
    }

    // BOTTOM UP
    private int change3(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j < amount; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (coins[i] > j) {
                    dp[i][j] = dp[i+1][j];
                } else {
                    dp[i][j] = dp[i][j - coins[i]] + dp[i+1][j];
                }
            }
        }
        return dp[0][amount];
    }
}
