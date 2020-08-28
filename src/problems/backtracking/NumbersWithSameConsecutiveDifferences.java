package problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 * You may return the answer in any order.
 */
public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(numsSameConsecDiff(3, 7)));
    }

    private static int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list = new ArrayList<>();
        helper(N, K, new StringBuilder(), 0, list);

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private static void helper(int n, int k, StringBuilder sb, int prev, List<Integer> ans) {
        if (sb.length() == n) {
            // leading 0 is valid only for 0 itself (n=1)
            if (n == 1 || sb.charAt(0) != '0') {
                ans.add(Integer.parseInt(sb.toString()));
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            // add all numbers when starting
            if (sb.length() == 0) {
                sb.append(i);
                helper(n, k, sb, i, ans);
                sb.deleteCharAt(sb.length() -1);
            } else if (Math.abs(prev-i) == k) {
                sb.append(i);
                helper(n, k, sb, i, ans);
                sb.deleteCharAt(sb.length() -1);
            }
        }
    }
}
