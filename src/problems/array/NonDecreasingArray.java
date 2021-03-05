package problems.array;

/**
 *
 */
public class NonDecreasingArray {
    public static void main(String[] args) {

    }

    private static boolean checkPossibility(int[] a) {
        int modified = 0;
        int prev = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < prev) {
                if (modified++ > 0) {
                    return false;
                }
                if (i >= 2 && a[i] < a[i-2]) {
                    continue; // keep same prev
                }
            }
            prev = a[i];
        }
        return true;
    }
}
