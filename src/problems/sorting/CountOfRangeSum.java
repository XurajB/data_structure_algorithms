package problems.sorting;

/**
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 */
public class CountOfRangeSum {
    public static void main(String[] args) {
        int[] nums = {-2,5,-1};
        System.out.println(new CountOfRangeSum().countRangeSum(nums, -2, 2));
    }

    // there is a n^2 naive solution. but we want to do better than that. the next best target would be linearithmic-like O((nlogn)^b) solution.
    // that's where divide and conquer idea comes from.
    // calculate prefix sum array, we are sorting the prefix sum
    // during the merge process (before merging) - we need to search for range sum between lower and upper

    // this solution is similar to #CountOfSmallerNumbersAfterSelf
    int count = 0;
    int lower;
    int upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        long[] temp = new long[nums.length + 1];
        sum[0] = 0;
        this.lower = lower;
        this.upper = upper;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + (long) nums[i - 1];
        }

        mergesort(sum, 0, sum.length - 1, temp);
        return count;
    }

    private void mergesort(long[] sum, int start, int end, long[] temp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergesort(sum, start, mid, temp);
        mergesort(sum, mid + 1, end, temp);
        merge(sum, start, mid, end, temp);
    }

    private void merge(long[] sum, int start, int mid, int end, long[] temp) {
        int right = mid + 1;
        int index = start;

        int low = mid + 1, high = mid + 1;

        for (int left = start; left <= mid; left++) {

            // only this part is unique
            while (low <= end && sum[low] - sum[left] < lower) {
                low++;
            }
            while (high <= end && sum[high] - sum[left] <= upper) {
                high++;
            }

            // merge part
            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }

            temp[index++] = sum[left];

            // count
            count += high - low;
        }
        while (right <= end) {
            temp[index++] = sum[right++];
        }
        for (int i = start; i <= end; i++) {
            sum[i] = temp[i];
        }
    }
}
