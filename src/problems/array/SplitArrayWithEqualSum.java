package problems.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
 *
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 * where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
 */
public class SplitArrayWithEqualSum {
    public static void main(String[] args) {
        System.out.println(splitArray(new int[] {1,2,1,2,1,2,1}));
    }

    // O(N^2)
    private static boolean splitArray(int[] nums) {
        // we are looking for 3 indices, remaining 4 parts has to be minimum 1 length
        if (nums == null || nums.length < 7) {
            return false;
        }
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }

        // middle part
        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> leftSums = new HashSet<>(); // store sums that we can get by splitting left half into 2 such that sums are equal

            // all splits of the left half nums[0, j-1]
            for (int i = 1; i < j - 1; i++) {
                int first = preSum[i-1]; // sum: 0, i-1
                int second = preSum[j-1] - preSum[i]; // sum: i+1, j-1
                if (first == second) {
                    leftSums.add(first);
                }
            }

            // all splits of right half nums[j+1, nums.length - 1]
            for (int k = j + 1; k < nums.length - 1; k++) {
                int third = preSum[k - 1] - preSum[j]; // sum: j+1, k-1
                int fourth = preSum[nums.length - 1] - preSum[k]; // sum: k+1, nums.length - 1
                if (third == fourth) {
                    if (leftSums.contains(third)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // O(N^3)
    private static boolean splitArray2(int[] nums) {
        if (nums.length < 7) {
            return false;
        }

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i]; // note: sum[i] = sum upto i
        }
        for (int i = 1; i < nums.length - 5; i++) {
            int sum1 = sum[i-1];
            for (int j = i + 2; j < nums.length - 3; j++) {
                int sum2 = sum[j-1] - sum[i];
                for (int k = j + 2; k < nums.length -1; k++) {
                    int sum3 = sum[k-1] - sum[j];
                    int sum4 = sum[nums.length - 1] - sum[k];
                    if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
