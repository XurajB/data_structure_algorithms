package problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 */
public class FactorCombinations {
    public static void main(String[] args) {
        System.out.println(getFactors(12));
    }

    private static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n == 0 || n == 1) {
            return ans;
        }
        List<Integer> factors = generateFactors(n);
        helper(n, 0, 1, factors, new ArrayList<>(), ans);
        return ans;
    }

    private static void helper(int n, int index, int product, List<Integer> factors, List<Integer> current, List<List<Integer>> ans) {
        if (product == n) {
            ans.add(new ArrayList<>(current));
            return;
        }
        if (product > n) {
            return;
        }
        for (int i = index; i < factors.size(); i++) {
            current.add(factors.get(i));
            helper(n, i, product * factors.get(i), factors, current, ans);
            current.remove(current.size() - 1);
        }
    }

    private static List<Integer> generateFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int half = n / 2;
        for (int i = 2; i <= half; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
}
