package problems.binarysearch;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such
 * that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 */
public class ContainsDuplicate3 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsNearbyAlmostDuplicate(nums, 3, 0));
    }

    // we need to maintain window size of k of the previous values and query bounds
    // the best data structure to do ths is BST.
    // long because overflow during diff
    // O(NlogK)
    private static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long) nums[i]);
            Long ceiling = set.ceiling((long) nums[i]);
            if ((floor != null && nums[i] - floor <= t ) ||
                    (ceiling != null && ceiling - nums[i] <= t)) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i-k]);
            }
        }
        return false;
    }
}
