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
        int first = search(nums, target);
        int second = search(nums, target + 1); // if target+1 is not present, it will still return left most position as if it is present

        return second - first > nums.length / 2;
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
