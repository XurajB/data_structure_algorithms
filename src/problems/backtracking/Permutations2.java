package problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, new boolean[nums.length]);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> current, int[] nums, boolean[] used) {
        if (current.size() == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(ans, current, nums, used);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
