package problems.string;

/**
 * strstr - locate a substring ( needle ) in a string ( haystack )
 * something like - String.indexOf(string)
 */
public class StrStr {
    public static void main(String[] args) {
        String a = "helloworld";
        String b = "worl";
        System.out.println(strStr(a, b));
    }

    public static int strStr(final String A, final String B) {
        int posb = 0;
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (c == B.charAt(posb)) {
                posb++;
            } else {
                posb = 0;
            }
            if (posb == B.length()) {
                return i - posb + 1;
            }
        }
        return -1;
    }
}
