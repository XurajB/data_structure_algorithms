package problems.binarysearch;

/**
 * Find first occurrence of target
 */
public class SearchFirstOccurrence {
    public static void main(String[] args) {
        int[] nums = {1,0,2,3,4,4,7,8};
        System.out.println(findFirstOccurrence(nums, 4));
    }

    // similar to FirstBadVersion
    private static int findFirstOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}
