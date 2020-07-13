package problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 */
public class CombinationSum3 {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }

    // O(C(9,k)) = O(9^k)
    // space: k
    private static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(k, n, 1, 0, new ArrayList<>(), ans);
        return ans;
    }

    private static void backtrack(int k, int n, int index, int sum, List<Integer> current, List<List<Integer>> ans) {
        if (current.size() == k && sum == n) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i <= 9; i++) {
            current.add(i);
            backtrack(k, n, i+1, sum+i, current, ans);
            current.remove(current.size() - 1);
        }
    }
}
