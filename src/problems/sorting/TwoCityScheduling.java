package problems.sorting;

import java.util.Arrays;

/**
 * There are 2N people a company is planning to interview.
 * The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 */
public class TwoCityScheduling {

    public static void main(String[] args) {
        int[][] costs = {{10,20},{30,200},{400,50},{30,20}};
        System.out.println(twoCitySchedCost(costs));
    }

    private static int twoCitySchedCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        Arrays.sort(costs, (a, b) -> {
            // compare delta, smallest delta first
            int delta = a[0] - a[1] - (b[0] - b[1]);
            System.out.println(Arrays.toString(a) + " " + Arrays.toString(b) + "  delta - " + delta);
            return delta;
        });

        int total = 0;

        for (int i = 0; i < n; i++) {
            if (i < n/2) {
                total += costs[i][0];
            } else {
                total += costs[i][1];
            }
        }

        return total;
    }
}
