package problems.slidingwindow;

/**
 * In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray of length K and simultaneously
 * changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * Return the minimum number of K-bit flips required so that there is no 0 in the array.  If it is not possible, return -1.
 */
public class MinimumNumberOfKConsecutiveBitFlips {
    public static void main(String[] args) {
        int[] A = {0,0,0,1,0,1,1,0};
        System.out.println(minKBitFlips(A, 3));
    }

    // O(n*k)
    // Find O(N) solution TODO
    // going with greedy way - whenever we see a 0, flip k consecutive bits
    private static int minKBitFlips(int[] A, int K) {
        int end = 0;
        int count = 0;
        while (end < A.length) {
            if (A[end] == 0) {
                if (end + K > A.length) {
                    return -1;
                }
                for (int j = end; j < end+K; j++) {
                    A[j] ^= 1;
                }
                count++;
            } else {
                end++;
            }
        }
        return count;
    }
}
