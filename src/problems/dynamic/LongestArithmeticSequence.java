package problems.dynamic;

import java.util.HashMap;

/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 * https://leetcode.com/problems/longest-arithmetic-sequence/
 */
public class LongestArithmeticSequence {
    public static void main(String[] args) {
        int[] a = {20,1,15,3,10,5,8};
        System.out.println(longestArithSeqLength(a));
    }

    // run two loops, put the difference with count in hashmap
    // O(N^2), O(N)
    public static int longestArithSeqLength(int[] A) {
        int res = 2; // min ans
        int n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n]; // (difference, frequencies)
        for (int j = 0; j < n; j++) {
            dp[j] = new HashMap<>();
            for (int i = 0; i < j; i++) { // i < j because dp[j] has not been initialized after i
                int d = A[j] - A[i];
                dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[j].get(d));
            }
        }
        return res;
    }
}
