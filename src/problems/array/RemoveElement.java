package problems.array;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        System.out.println(removeElement(nums, 2));
    }

    // O(N), O(1)
    private static int removeElement(int[] nums, int val) {
        int begin=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[begin++] = nums[i];
            }
        }
        return begin;
    }
}
