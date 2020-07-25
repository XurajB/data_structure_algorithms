package problems.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array nums sorted in ascending order,
 * return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.
 */
public class SplitArrayIntoConsecutiveSubseq {
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,5};
        System.out.println(isPossible(nums));
    }

    //O(N)
    private static boolean isPossible(int[] nums) {
        // we iterate through the array to get the frequency of all elements
        // we iterate one more time, for each element we either see if it can be appended to previously constructed sequence or if it can be start of a new sequence
        // if neither are true, return false
        Map<Integer, Integer> freq = new HashMap<>(); // (num, freq)
        Map<Integer, Integer> appendFreq = new HashMap<>(); // tail of existing sequence map. (next in seq, freq)

        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num: nums) {
            if (freq.get(num) == 0) {
                // we can't use this element
                continue;
            // check if we append this to already existing sequence starting at num
            } else if (appendFreq.getOrDefault(num, 0) > 0) {
                // append this to existing sequence
                appendFreq.put(num, appendFreq.get(num) - 1);
                // create space from next number in sequence, which is num+1
                appendFreq.put(num+1, appendFreq.getOrDefault(num+1, 0) + 1);
            // check if we can start our own sequence from num, in order to be a valid sequence it needs to have 3 consecutive numbers
            } else if (freq.getOrDefault(num+1, 0) > 0 && freq.getOrDefault(num+2, 0) > 0) {
                freq.put(num+1, freq.get(num+1) - 1);
                freq.put(num+2, freq.get(num+2) - 1);
                // create space for next number in this sequence
                appendFreq.put(num+3, appendFreq.getOrDefault(num+3, 0) + 1);
            } else {
                return false;
            }

            freq.put(num, freq.get(num) - 1);
        }
        return true;
    }
}
