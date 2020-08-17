package problems.slidingwindow;

import java.util.Arrays;

/**
 * Given an array of integers arr and an integer target.
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 */
public class NonOverlappingSubArrayTargetSum {
    public static void main(String[] args) {
        int[] arr = {3,2,2,4,3};
        System.out.println(minSumOfLengths(arr, 3));
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
}
