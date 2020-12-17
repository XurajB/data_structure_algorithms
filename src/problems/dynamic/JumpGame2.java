package problems.dynamic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 */
public class JumpGame2 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }

    // dp, bottom-up, N^2
    // TLE
    private static int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0; // takes 0 steps to reach 0
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                int next = Math.min(i+j, nums.length - 1);
                dp[next] = Math.min(dp[next], 1 + dp[i]);
            }
        }

        return dp[nums.length - 1];
    }

    // dp, top-down, O(N^2)
    private static int jump2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[nums.length - 1] = 0;
        return helper(nums, dp, 0);
    }

    private static int helper(int[] nums, int[] dp, int current) {
        if (dp[current] != Integer.MAX_VALUE) {
            return dp[current];
        }

        int min = Integer.MAX_VALUE;

        int maxReach = Math.min(current + nums[current], nums.length - 1);
        for (int i = current + 1; i <= maxReach; i++) {
            int steps = helper(nums, dp, i);
            if (steps != Integer.MAX_VALUE) {
                min = Math.min(min, steps+1);
            }
        }

        dp[current] = min;
        return dp[current];
    }

    /////// BFS, greedy
    // O(N^2)
    private static int jump3(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == nums.length - 1) {
                    return level;
                }
                for (int j = 1; j <= nums[current]; j++) {
                    int next = current + j;
                    if (next >= nums.length) {
                        break;
                    }
                    if (visited[next]) {
                        continue;
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            level++;
        }
        return -1;
    }

    /// greedy
    // O(N)
    private static int jump4(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int curFarthest = 0;
        int maxFarthest = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxFarthest) {
                return -1; // we can't reach this
            }
            maxFarthest = Math.max(maxFarthest, i + nums[i]);
            if (i < nums.length - 1 && i == curFarthest) { // no need to jump at nums.length - 1, eg [2,3,1,1,4]
                // need to move to next
                jumps++;
                curFarthest = maxFarthest;
            }
        }

        return jumps;
    }
}
