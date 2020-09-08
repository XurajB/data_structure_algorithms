package problems.string;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abcabc"));
    }

    private static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        // pattern must repeat at least twice
        // start with 1, n%0 is undefined
        for (int len = 1; len <= n/2; len++) {
            // the length has to be divisible by pattern lenght
            if (n%len == 0) {
                String pattern = s.substring(0, len);

                int i = len; // start of second pattern
                int j = i+len-1; // end of second pattern
                while (j < n) {
                    String next = s.substring(i, j+1);
                    if (!next.equals(pattern)) {
                        break;
                    }
                    i = i+len;
                    j = j+len;
                }
                if (i == n) {
                    return true;
                }
            }
        }
        return false;
    }
}
