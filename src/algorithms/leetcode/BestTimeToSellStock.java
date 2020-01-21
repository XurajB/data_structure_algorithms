package algorithms.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/564/
 */
public class BestTimeToSellStock {

    public static void main(String[] args) {
        int[] input = {7,1,5,3,6,4};
        System.out.println(maxProfit(input)); // ans 7
    }

    // time - O(N), space - O(1)
    private static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < (prices.length); i++) {
            if (prices[i] > prices[i - 1]) {
                max = max + prices[i] - prices[i-1];
            }
        }

        return max;
    }
}
