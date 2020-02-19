package algorithms.leetcode;

/**
 * Say you have an array, A, for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToSellStock {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }

    private static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int minUntil = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minUntil = Math.min(minUntil, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minUntil);
        }
        return maxProfit;
    }
}
