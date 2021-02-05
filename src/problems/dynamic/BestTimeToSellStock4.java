package problems.dynamic;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */
public class BestTimeToSellStock4 {
    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[] {2,4,1}));
    }

    // i = day, j = transaction
    // T[i][j] => max (T[i-1][j] (not transacting on jth day), best you can get by transacting on jth day: (prices[j] - prices[m] + T[m][i-1])
    // similar to BestTimeToSell3, we need to perform the same operation for k times
    // O(nk)
    private static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        // for k days, we can perform max 2k transactions (buy, sell)
        // if 2k >= n, make as many transaction as possible
        if (2*k >= n) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i-1]) {
                    maxProfit += prices[i] - prices[i-1];
                }
            }
            return maxProfit;
        }

        int[][] dp = new int[k + 1][n]; // k is 1 based
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0]; // we started i = 1, money we spent buying stock at day 0 (debt, so negative)
            for (int j = 1; j < n; j++) {
                // two choices: do nothing (dp[i][j-1]) or sell at price prices[j]
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax); // max between our old profit and profit if we are going to sell the jth stock
                tmpMax =  Math.max(tmpMax, dp[i - 1][j - 1] - prices[j]); // maximize our debt (coz we are using -ve). dp[i-1][j-1] because according to ^ we used yesterdays last transaction. so going one step back
            }
        }
        return dp[k][n - 1];
    }
}
