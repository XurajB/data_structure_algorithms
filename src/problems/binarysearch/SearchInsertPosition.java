package problems.binarysearch;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums, 7));
    }

    // Log(N)
    private static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}
