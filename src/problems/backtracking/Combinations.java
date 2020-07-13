package problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 */
public class Combinations {
    public static void main(String[] args) {
        System.out.println(combine(5, 3));
    }

    // O(k * (n! / (n-k)!k!)
    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(n, k, 1, new ArrayList<>(), ans);
        return ans;
    }

    private static void backtrack(int n, int k, int index, List<Integer> current, List<List<Integer>> ans) {
        if (current.size() == k) {
            ans.add(new ArrayList<>(current));
        }

        for (int i = index; i <= n; i++) {
            current.add(i);
            backtrack(n, k, i+1, current, ans);
            current.remove(current.size() - 1);
        }
    }
}
