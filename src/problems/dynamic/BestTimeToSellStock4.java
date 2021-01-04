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
        // if 2k < n, then it will be BestTimeToSellStock1, where we perform as many transaction as possible because 2k transaction is not possible
        if (2*k < n) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i-1]) {
                    maxProfit += prices[i] - prices[i-1];
                }
            }
            return maxProfit;
        }

        int[][] dp = new int[k+1][n];
        // we can maximize by either not doing any transaction or transacting n times for that day: whichever is max
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] - localMax);
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }
}
