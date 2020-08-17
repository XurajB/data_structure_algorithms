package problems.slidingwindow;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Explanation: after flipping 2 0s
 * [1,1,1,0,0,1,1,1,1,1,1]
 */
public class MaxConsecutiveOnes3 {
    public static void main(String[] args) {
        int[] A = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(longestOnes(A, 2));
    }

    // O(N)
    // we use sliding widow to assume we flipped k digits
    private static int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;

        int max = Integer.MIN_VALUE;

        while (right < A.length) {
            if (A[right] == 0) {
                K--; // this indicates we consumed 1 flip
            }
            while (K < 0) { // we consumed all flips
                if (A[left] == 0) {
                    K++;
                }
                left++;
            }
            max = Math.max(max, right-left+1);
            right++;
        }
        return max;
    }
}
