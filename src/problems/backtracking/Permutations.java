package problems.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * https://leetcode.com/problems/permutations/
 * https://www.interviewbit.com/problems/all-unique-permutations/
 */
public class Permutations {
    public static void main(String[] args) {
        int[] n = {1,2,3};
        System.out.println(permute(n));
    }

    // O(n!) time, O(n!) space
    // each n we have n choices
    private static List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList<>();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }

    private static void backtrack(int n,
                          ArrayList<Integer> nums,
                          List<List<Integer>> output,
                          int index) {
        // if all integers are used up
        if (index == n)
            output.add(new ArrayList<>(nums));
        for (int i = index; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, index, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, index + 1);
            // backtrack
            Collections.swap(nums, index, i);
        }
    }

    private void backtrack2(List<List<Integer>> ans, List<Integer> current, int[] nums) {
        if (current.size() == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }
        for (int num : nums) {
            if (current.contains(num)) {
                continue;
            }
            current.add(num);
            backtrack2(ans, current, nums);
            current.remove(current.size() - 1);
        }
    }
}
