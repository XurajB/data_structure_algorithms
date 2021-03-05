package problems.binarysearch;

/**
 * Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.
 * A majority element is an element that appears more than N/2 times in an array of length N.
 */
public class CheckIfANumberIsMajority {
    public static void main(String[] args) {
        int[] nums = {2,4,5,5,5,5,5,6,6};
        System.out.println(isMajorityElement(nums, 5));
    }

    private static boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        // find left and right bound of the target
        int first = search(nums, target);

        int next = first + nums.length / 2;
        return next < nums.length && nums[next] == target;
    }

    // find the first occurrence of target
    private static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
