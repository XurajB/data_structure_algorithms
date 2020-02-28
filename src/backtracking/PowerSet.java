package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, returns all possible subsets (the power set)
 * Note: the solution set must not contain duplicate subsets
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * https://leetcode.com/problems/subsets/
 */
public class PowerSet {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }

    // O(2^n), space: O(n)
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        generateSubsets(0, nums, list, answer);
        return answer;
    }

    private static void generateSubsets(int index, int[] nums, List<Integer> current, List<List<Integer>> answer) {
        answer.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsets(i + 1, nums, current, answer);
            current.remove(current.size() - 1);
        }
    }
}
