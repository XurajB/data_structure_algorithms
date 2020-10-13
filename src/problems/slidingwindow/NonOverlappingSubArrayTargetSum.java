package problems.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr and an integer target.
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 */
public class NonOverlappingSubArrayTargetSum {
    public static void main(String[] args) {
        int[] arr = {3,2,2,4,3};
        System.out.println(minSumOfLengths2(arr, 3));
    }

    // O(N), O(N)
    private static int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        int sum = 0;
        int start = 0;
        int ans = Integer.MAX_VALUE;
        int bestSoFar = Integer.MAX_VALUE;
        int end = 0;
        while (end < arr.length) {
            sum += arr[end];

            while (sum > target){
                sum -= arr[start];
                start++;
            }
            if (sum == target) {
                if (start > 0 && best[start - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, best[start - 1] + end - start + 1); // sum of current + last best
                }
                bestSoFar = Math.min(bestSoFar, end - start + 1);
            }

            best[end] = bestSoFar;
            end++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // O(N), O(N)
    // will not work on -ve or 0 elements
    private static int minSumOfLengths2(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target < 1) {
            return -1;
        }

        Map<Integer, Integer> map = new HashMap<>(); // sum, index
        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            map.put(sum, i);
        }

        sum = 0;
        int ans = Integer.MAX_VALUE;
        int lsize = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - target)) {
                lsize = Math.min(lsize, i - map.get(sum - target));
            }
            if (map.containsKey(sum + target) && lsize != Integer.MAX_VALUE) {
                ans = Math.min(ans, map.get(sum + target) - i + lsize);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
