package problems.sorting;

/**
 *
 */
public class CountOfRangeSum {
    public static void main(String[] args) {

    }

    // this solution is similar to #CountOfSmallerNumbersAfterSelf
    int count = 0;
    int lower;
    int upper;
    int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        long[] temp = new long[nums.length + 1];
        sum[0] = 0;
        this.upper = upper;
        this.lower = lower;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        mergeSort(sum, temp, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(long[] sum, long[] temp, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(sum, temp, start, mid);
            mergeSort(sum, temp, mid + 1, end);

            merge(sum, temp, start, mid, end);
        }
    }

    private void merge(long[] sum, long[] temp, int start, int mid, int end) {
        int right = mid + 1;
        int index = start;

        int low = mid + 1;
        int high = mid + 1;

        for (int left = start; left <= mid; left++) {
            while (low <= end && sum[low] - sum[left] < lower) {
                low++;
            }
            while (high <= end && sum[high] - sum[left] <= upper) {
                high++;
            }
            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }
            temp[index++] = sum[left];
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
