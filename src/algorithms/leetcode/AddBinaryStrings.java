package algorithms.leetcode;

/**
 * Add two binary strings and return result as a string
 * https://leetcode.com/problems/add-binary/
 */
public class AddBinaryStrings {
    public static void main(String[] args) {
        System.out.println(addBinaryStrings("1010110111001101101000", "1000011011000000111100110"));
    }

    private static String addBinaryStrings(String a, String b) {
        int n = a.length(), m = b.length();
        if (n < m) return addBinaryStrings(b, a);
        int L = Math.max(n, m);

        StringBuilder sb = new StringBuilder();

        int carry = 0, j = m - 1;
        for(int i = L - 1; i >= 0; i--) {
            if (a.charAt(i) == '1') carry++;
            if (j >= 0 && b.charAt(j--) == '1') {
                carry++;
            }

            if (carry % 2 == 1) sb.append('1');
            else sb.append('0');

            carry /= 2;
        }
        if (carry == 1) sb.append('1');
        sb.reverse();

        return sb.toString();
    }
}
