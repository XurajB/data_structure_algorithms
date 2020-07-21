package problems.heap;

import java.util.TreeMap;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,4,5,6};
        System.out.println(isPossibleDivide(nums, 4));
    }

    // Exactly same solution as #HandOfStraights
    private static boolean isPossibleDivide(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        while (!map.isEmpty()) {
            int first = map.firstKey();
            for (int i = 1; i < k; i++) {
                int next = first + i;
                if (!map.containsKey(next)) {
                    return false;
                }
                map.put(next, map.get(next) - 1);
                if (map.get(next) == 0) {
                    map.remove(next);
                }
            }
            map.put(first, map.get(first) - 1);
            if (map.get(first) == 0) {
                map.remove(first);
            }
        }
        return true;
    }
}
