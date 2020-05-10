package problems.dynamic;

/**
 * A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs
 */
public class NumberOfSteps {

    public static void main(String[] args) {
        int n = 4;
        System.out.println(countWays(n, new int[n+1]));
        System.out.println(countWays(n));
    }

    // number of ways will overflow an integer when n = 37
    // recursive
    private static int countWays(int n, int[] map) {
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (map[n] > 0) {
            return map[n];
        } else {
            map[n] = countWays(n - 1, map)  +
                    countWays(n - 2, map) +
                    countWays(n - 3, map);
            return map[n];
        }
    }

    // non recursive
    private static int countWays(int n) {
        int[] map = new int[n+1];
        map[0] = 1;
        map[1] = 1;
        map[2] = 2;

        for (int i = 3; i <= n; i++) {
            map[i] = map[i - 1] + map[i - 2] + map[i - 3];
        }

        return map[n];
    }
}
