package problems.binarysearch;

/**
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 * https://leetcode.com/problems/missing-element-in-sorted-array/
 */
public class MissingElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 7, 9, 10};
        System.out.println(missingElement(nums, 5));
    }

    private static int missingElement(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i-1] - 1;
            if (diff >= k) {
                return nums[i-1] + k;
            } else {
                k = k - diff;
            }
        }

        return nums[nums.length - 1] + k;
    }

    // binary search
    private static int missingElement3(int[] nums, int k) {
        int left = 0, right = nums.length;
        int target = nums[0] + k;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target + mid - 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return target + left - 1;
    }

    // log(n)
    // two cases: if missingNum < k, return diff
    // if not then use binary search to find the index in the array where the kth missing number will be located
    private static int missingElement2(int[] nums, int k) {
        int len = nums.length;
        int lo = 0;
        int hi = len - 1;

        int countMissing = nums[len - 1] - nums[0] - len  + 1;
        if (countMissing < k) {
            return nums[len - 1] + k - countMissing;
        }

        while (lo < hi -1) {
            int mid = lo + (hi - lo) / 2;
            int newCount = nums[mid] - nums[lo] - (mid - lo);

            if (newCount >= k) {
                hi = mid;
            } else {
                k =- newCount;
                lo = mid;
            }
        }

        return nums[lo] + k;
    }
}
