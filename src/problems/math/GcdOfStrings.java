package problems.math;

/**
 * For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)
 * Return the largest string X such that X divides str1 and X divides str2.
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
public class GcdOfStrings {
    public static void main(String[] args) {
        String str1 = "ABABAB";
        String str2 = "ABAB";
        System.out.println(gcdOfStrings(str1, str2));
    }

    // O(N)
    private static String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return a % b == 0 ? b : gcd(b, a % b);
    }
}
