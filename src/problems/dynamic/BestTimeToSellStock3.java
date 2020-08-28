package problems.dynamic;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 */
public class BestTimeToSellStock3 {
    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(twoTrades(prices));
    }
    /*
    Let’s say we have an array of prices.
    We introduce idea of best trade ​up to​​i​.
    This is the most money we can make with one trade, given the subarray ​prices[0..i]​.
    We can create this ​best_till_i​ array for each ​i​ in O(n) time and O(n) space.
    Now, let’s say we create another array ​best_from_i​, which is the most money we can makestarting at ​i​.
    I.e, with the subarray ​prices[i..prices.length-1]
    Now, to find the maximum of 2 trades over the entire price array:
    for i -> 0 to prices.length
             max_2_trades = Max(max_2_trades, best_till_i[i] + best_from_i[i])
     */
    private static int twoTrades(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int[] bestTill = new int[prices.length];
        int minSoFar = Integer.MAX_VALUE, maxDiff = 0;
        for (int i = 0; i < prices.length; i++) {
            minSoFar = Math.min(minSoFar, prices[i]);
            maxDiff = Math.max(maxDiff, prices[i] - minSoFar);
            bestTill[i] = maxDiff;
        }

        int[] bestFrom = new int[prices.length];
        int maxSoFar = Integer.MIN_VALUE;
        maxDiff = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            maxSoFar = Math.max(maxSoFar, prices[i]);
            maxDiff = Math.max(maxDiff, maxSoFar - prices[i]);
            bestFrom[i] = maxDiff;
        }

        int maxTwoTrades = 0;
        for (int i = 0; i < prices.length; i++) {
            maxTwoTrades = Math.max(maxTwoTrades, bestTill[i] + bestFrom[i]);
        }
        return maxTwoTrades;
    }
}
