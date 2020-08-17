package problems.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        int[] nums = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(nums)));
    }

    // monotonous stack
    // O(N)
    private static int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int index = stack.poll();
                ans[index] = i - index;
            }
            stack.push(i);
        }
        return ans;
    }
}
