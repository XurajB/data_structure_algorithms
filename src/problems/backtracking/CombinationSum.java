package problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * https://www.interviewbit.com/problems/combination-sum/
 * https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] a = {2,3,6,7,7};
        System.out.println(subsets(a, 7));
    }

    private static List<List<Integer>> subsets(int[] nums, int target) {
        Arrays.sort(nums); // not really needed, to make sure results are ordered
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generateSets(nums, answer, current, 0, target, 0);
        return answer;
    }

    private static void generateSets(int[] nums, List<List<Integer>> answer, List<Integer> current, int sum, int target, int index) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            answer.add(new ArrayList<>(current));
            index = index + 1;
        }

        for (int i = index; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i+1]) { //no repeated answer (in case input has duplicate integers). works for sorted list
                continue;
            }
            current.add(nums[i]);
            generateSets(nums, answer, current, sum + nums[i], target, i);
            current.remove(current.size() - 1);
        }
    }
}
