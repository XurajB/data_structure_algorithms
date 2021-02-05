package problems.dynamic;

/**
 * In a country popular for train travel, you have planned some train travelling one year in advance.
 * The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in 3 different ways:
 *
 * a 1-day pass is sold for costs[0] dollars;
 * a 7-day pass is sold for costs[1] dollars;
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 *
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 */
public class MinimumCostForTickets {
    public static void main(String[] args) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        System.out.println(minCostTickets(days, costs));
    }

    // O(N), n = last travel days
    private static int minCostTickets(int[] days, int[] costs) {
        int n = days[days.length - 1]; // last travel day
        int[] dp = new int[n+1];
        dp[0] = 0;

        int d = 0; // days index
        for (int i = 1; i <= n; i++) {
            // if this is a travel day
            if (days[d] == i) {
                int min = dp[i-1] + costs[0];
                // for case like this, we have to check if weekly pass is cheaper than daily
                //[1,4,6,7,8,20]
                //[7,2,15]
                min = Math.min(min, costs[1] + (i >= 7 ? dp[i-7] : 0));
                min = Math.min(min, costs[2] + (i >= 30 ? dp[i-30] : 0));

                dp[i] = min;
                d++;
            } else {
                // if not travel day, cost remains the same
                dp[i] = dp[i-1];
            }
        }

        return dp[n];
    }

    // generic solution if durations was also variable
    private static int minCostTickets2(int[] days, int[] costs) {
        int n = days[days.length - 1]; // last travel day
        int[] dp = new int[n+1];
        dp[0] = 0;

        int[] durations = {1,7,30};

        int d = 0; // days index
        for (int i = 1; i <= n; i++) {
            // if this is a travel day
            if (days[d] == i) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < durations.length; j++) {
                    int candidate = costs[j];
                    if (i >= durations[j]) {
                        candidate += dp[i - durations[j]];
                    }
                    dp[i] = Math.min(dp[i], candidate);
                }
                d++;
            } else {
                // if not travel day, cost remains the same
                dp[i] = dp[i-1];
            }
        }

        return dp[n];
    }
}
