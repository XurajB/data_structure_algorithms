package problems.array;

/**
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 *
 * Return 0 if there is no mountain.
 */
public class LongestMountainInArray {
    public static void main(String[] args) {
        System.out.println(longestMountain(new int[] {1,2,3,2,1,0,2,3,1}));
    }
    private static int longestMountain(int[] A) {
        int max = 0;
        int dec = 0;
        int inc = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1]) {
                if (dec > 0) {
                    inc = 0;
                }
                inc++;
                dec = 0;
            } else if (A[i] < A[i-1]) {
                if (inc > 0) {
                    dec++;
                    max = Math.max(max, inc+dec+1);
                }
            } else {
                inc = 0;
                dec = 0;
            }
        }

        return max;
    }
}
