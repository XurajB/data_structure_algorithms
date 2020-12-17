package problems.array;

import java.util.*;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 */
public class RandomPickIndex {
    Map<Integer, List<Integer>> map;
    Random random;

    // depending on whether we have enough space to save the data
    // we can do this solution or reservoir sampling
    public RandomPickIndex(int[] nums) {
        random = new Random();
        map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        if (!map.containsKey(target)) {
            return -1;
        }

        int size = map.get(target).size();
        return map.get(target).get(random.nextInt(size));
    }
}
