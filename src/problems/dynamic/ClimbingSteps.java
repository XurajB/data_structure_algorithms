package problems.dynamic;

import java.util.Arrays;
import java.util.Collections;

/**
 * You are given a number of steps. You can climb 1 step, 3 steps or 5 steps at a time.
 * Calculate the number of ways to get to the top of the steps
 */
public class ClimbingSteps {
    public static void main(String[] args) {
        System.out.println(countSteps(10));
        System.out.println(countSteps2(10));
        System.out.println(countSteps3(10));

        //Arrays.sort(args, Collections.reverseOrder());
    }

    // Top down
    private static int countSteps(int steps) {
        if (steps < 0) {
            return 0;
        }
        if (steps == 0) {
            return 1;
        }
        return countSteps(steps - 1) +
                countSteps(steps - 3) +
                countSteps(steps - 5);
    }

    // top down
    private static int countSteps2(int steps) {
        int[] dp = new int[steps + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int n1 = i-1<0 ? 0 : dp[i-1];
            int n3 = i-3<0 ? 0 : dp[i-3];
            int n5 = i-5<0 ? 0 : dp[i-5];
            dp[i] = n1 + n3 + n5;
        }

        return dp[steps];
    }

    // bottom up
    private static int countSteps3(int steps) {
        int[] dp = new int[steps + 1];
        dp[0] = 1;

        for (int i = 0; i < steps; i++) {
            if (i+1 < dp.length) {
                dp[i+1] += dp[i];
            }
            if (i+3 < dp.length) {
                dp[i+3] += dp[i];
            }
            if (i+5 < dp.length) {
                dp[i+5] += dp[i];
            }
        }

        return dp[steps];
    }
}
