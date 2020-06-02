package problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 */
public class Subsets2 {
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        System.out.println(subsetsWithDup(nums));
    }

    private static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, nums, new ArrayList<>(), 0);
        return ans;
    }

    private static void backtrack(List<List<Integer>> ans, int[] nums, List<Integer> current, int index) {
        if (index > nums.length) {
            return;
        }

        ans.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            // skip duplicate
            if (i > index && nums[i] == nums[i-1]) {
                continue;
            }
            current.add(nums[i]);
            backtrack(ans, nums, current, i + 1);
            current.remove(current.size() - 1);
        }
    }

}
