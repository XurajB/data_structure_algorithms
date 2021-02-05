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

    ///////////
    // reservoir sampling
    // when the data-set has unknown length
    // probability of selecting is: 1/f (f is the frequency of target)
    int[] nums;
    Random rnd;
    public int pick2(int target) {
        int result = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                // increase total candidate count
                count++;

                // if count was 1, probability of getting 0 is 100%
                // as it increases, the probability of getting 0 also increases. So random index
                // we only do this if target matches nums[i]
                if (rnd.nextInt(count) == 0) {
                    result = i;
                }
            }
        }
        return result;
    }
}
