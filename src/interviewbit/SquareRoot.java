package interviewbit;

/**
 * Given an integar A.
 * Compute and return the square root of A.
 *https://www.interviewbit.com/problems/square-root-of-integer/
 */
public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(sqrt(17));
    }

    // Time: O(logN), Space: O(1)
    private static int sqrt(int A) {
        if (A == 0 || A == 1) {
            return A;
        }
        int low = 0;
        int high = A/2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (mid*mid == A || (mid*mid < A && (mid+1)*(mid+1) > A)) {
                return mid;
            } else if (mid*mid > A) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
