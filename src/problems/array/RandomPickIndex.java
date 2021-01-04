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
                // we pick index with probability 1/count (reservoir sampling)
                // java Random: All possible values are produced with (approximately) equal probability.
                if (rnd.nextInt(count) == 0) {
                    // ^ it could be = 0 or count - 1, what is the probability of selecting random = 0? => equal probability.
                    // it has probability 1 when count = 1, 1/2 when count = 2, 2/3 when count = 3. total: 1 * 1/2 * 2/3 = 1/3.
                    result = i;
                }
            }
        }
        return result;
    }
}
