package problems.dynamic;

public class BestTimeToBuyAndSellWithCooldown {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit(prices));
    }

    // O(N), O(N)
    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int len = prices.length;
        int[] cooldown = new int[len]; // day i is cooldown day
        int[] sell = new int[len]; // this means sell at day i

        sell[1] = prices[1] - prices[0];
        for (int i = 2; i < prices.length; i++) {
            cooldown[i] = Math.max(cooldown[i-1], sell[i-1]); // since it is a cooldown day, max profit will be cooldown so far or if we sold yesterday
            sell[i] = prices[i] - prices[i-1] + Math.max(sell[i-1], cooldown[i-2]); // not a cool down day so we sell. profit today + max profit yesterday or cooldown 2 days ago
        }
        // we are only using data until i-2 so we can improve space complexity by introducing two variables
        return Math.max(sell[len-1], cooldown[len-1]);
    }
}
