package problems.binarysearch;

/**
 * Given a sorted array of Integers, find the target.
 * If the target is not found,return the element closest to the target.
 * For example,A = [1,2,4,5,7,8,9], Target = 6 -> Output Index = 3 or 4 (since both 5 and 7 are equally close)
 */
public class FindClosestToTarget {
    public static void main(String[] args) {
        int[] nums = {10,20,30,40};
        System.out.println(findClosest(nums, 40));
    }

    private static int findClosest(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        int result = 0;
        while (start <= end) {
            int mid = start + (end - start)/2;
            // check for closer
            if (Math.abs(nums[mid] - target) < Math.abs(nums[result] - target)) {
                result = mid;
            }
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }
}
