package problems.twopointers;

/**
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are less than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 * https://leetcode.com/problems/reverse-string-ii/
 */
public class ReverseString2 {
    public static void main(String[] args) {
        System.out.println(reverseString("abcdefg", 2));
    }

    // O(N), O(N)
    private static String reverseString(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2*k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }
}
