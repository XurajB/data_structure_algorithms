package problems.array;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 * Return true if and only if the given array A is monotonic.
 */
public class MonotonicArray {
    public static void main(String[] args) {
        System.out.println(isMonotonic(new int[] {1,2,2,3}));
    }

    // O(N)
    public static boolean isMonotonic(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i+1] > A[i]) {
                decreasing = false;
            } else if (A[i+1] < A[i]) {
                increasing = false;
            }
        }

        return increasing || decreasing;
    }

    // O(N)
    public static boolean isMonotonic2(int[] A) {
        if (A.length == 0) {
            return false;
        }
        if (A.length == 2) {
            return true;
        }
        int i = 1;
        while (i < A.length && A[i-1] == A[i]) {
            i++;
        }
        if (i < A.length) {
            boolean isIncreasing = A[i] > A[i-1];
            while (i < A.length) {
                if (isIncreasing) {
                    if (A[i] < A[i-1]) {
                        return false;
                    }
                } else {
                    if (A[i] > A[i-1]) {
                        return false;
                    }
                }
                i++;
            }
        }
        return true;
    }
}
