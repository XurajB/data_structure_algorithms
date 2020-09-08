package problems.string;

/**
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 */
public class LargestTimeForGivenDigits {
    public static void main(String[] args) {
        int[] A = {1,2,3,4};
        System.out.println(largestTimeFromDigits(A));
    }

    // O(1)
    private static String largestTimeFromDigits(int[] A) {
        String ans = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }
                    String h = "" + A[i] + A[j];
                    String m = "" + A[k] + A[6-i-j-k]; // A[k] + remainder. i,j,k,l are permutation of 0,1,2,3. since i+j+k+l = 0+1+2+3=6, l = 6-i-j-k
                    String t = h + ":" + m;
                    // construct valid time and compare with previous ans
                    if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && ans.compareTo(t) < 0) {
                        ans = t;
                    }
                }
            }
        }
        return ans;
    }
}
