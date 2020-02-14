package algorithms.leetcode;

/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 */
public class LongestArithmeticSequence {
    public static void main(String[] args) {
        int[] a = {20,1,15,3,10,5,8};
        System.out.println(longestArithSeqLength(a));
    }


    public static int longestArithSeqLength(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }


        int count = 0;

        for (int i = 0; i < A.length - 1; i++) {

            int step = A[i + 1] - A[i];
            int count1 = 0;

            for (int j = i; j < A.length - 1; j++) {

                int step1 = A[j + 1] - A[j];

                if (step1 == step) {
                    count1++;
                }

            }

            count = Math.max(count, count1);


        }

        return count + 1;

    }
}
