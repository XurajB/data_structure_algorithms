package problems.dynamic;

/**
 * Given an integer array nums, return the number of longest increasing subsequences
 */
public class NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[] {1,3,5,4,7}));
    }

    // O(N^2), O(N)
    private static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }

        int[] lengths = new int[n];
        int[] counts = new int[n];

        for (int i = 0; i < n; i++) {
            lengths[i] = counts[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    } else if (lengths[j] + 1 > lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    }
                }
            }
        }

        int max = 0;
        int ans = 0;
        for (int length: lengths) {
            max = Math.max(max, length);
        }
        for (int i = 0; i < n; i++) {
            if (lengths[i] == max) {
                ans += counts[i];
            }
        }
        return ans;
    }
}
