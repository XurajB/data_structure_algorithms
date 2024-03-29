package problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    // O(n * 2 ^ n), O(n)
    // we are repeating n times and every single decomposition of n that is a palindrome
    private static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        // similar to permutation problem
        backtrack(ans, new ArrayList<>(), s, 0);
        return ans;
    }

    private static void backtrack(List<List<String>> ans, List<String> current, String s, int index) {
        if (index == s.length()) {
            ans.add(new ArrayList<>(current));
            return;
        }
        // first choice is: a, then every decomposition of remaining ab
        // then aa..
        // then aab
        for (int i = index; i < s.length(); i++) { // we dont start from 0, because the parts are continuous
            if (isPalindrome(s, index, i)) {
                current.add(s.substring(index, i + 1)); // i + 1, because substring
                backtrack(ans, current, s, i + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
