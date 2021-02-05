package problems.array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 */
public class MissingRanges {
    public static void main(String[] args) {
        System.out.println(findMissingRanges(new int[] {0,1,3,50,75}, 0, 99));
    }

    // O(N)
    private static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (nums.length == 0) {
            String next = formatRange(lower, upper);
            ans.add(next);
            return ans;
        }

        if (nums[0] > lower) {
            ans.add(formatRange(lower, nums[0] - 1));

        }
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i-1] > 1) {
                ans.add(formatRange(nums[i-1] + 1, nums[i] - 1));
            }
        }

        if (nums[n - 1] < upper) {
            ans.add(formatRange(nums[n-1] + 1, upper));
        }

        return ans;
    }

    private static String formatRange(int low, int high) {
        if (low == high) {
            return String.valueOf(low);
        } else {
            return low + "->" + high;
        }
    }
}
