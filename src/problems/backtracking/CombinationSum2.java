package problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        int[] nums = {10,1,2,7,6,1,5};
        System.out.println(combinationSum2(nums, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // candidates contains dupes, we will nee to sort to figure dupes out later
        List<List<Integer>> ans = new ArrayList<>();
        generateSets(candidates, ans, new ArrayList<>(), 0, target, 0);
        return ans;
    }

    private static void generateSets(int[] nums, List<List<Integer>> answer, List<Integer> current, int sum, int target, int index) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i  > index && nums[i] == nums[i-1]) { // skip duplicates, only works on sorted array
                continue;
            }
            current.add(nums[i]);
            generateSets(nums, answer, current, sum + nums[i], target, i + 1); // i + 1, because we do not want to repeat same number
            current.remove(current.size() - 1);
        }
    }
}
