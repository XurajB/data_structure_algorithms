package problems.array;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 */
public class FindDuplicate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,1};
        System.out.println(findDuplicate(nums));
    }

    // we can't modify array
    // has to be O(1) space
    // runtime should be less than O(N^2)

    // the idea is to use similar concept of finding cycle in a linked list
    // one slow runner and one fast runner
    private static int findDuplicate(int[] nums) {
        // find the intersection point of two runners
        int fast = nums[0];
        int slow = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // find the entrance to the cycle
        slow = nums[0];
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }
}
