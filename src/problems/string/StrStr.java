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

    // O(nk)
    // better solution at O(n) using rabin karp: #SearchString
    private static int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() == 0)
            return -1;
        for (int i = 0; i < haystack.length(); i++) {
            // no enough places for needle after i
            if (i + needle.length() > haystack.length()) break;

            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j))
                    break;
                if (j == needle.length()-1)
                    return i;
            }
        }

        return -1;
    }

    // Rabin karp - pattern matching algorithm
    // first find the hash for needle using this:
    // example: abc => 0 * 26^2 + 1*26^1 + 2*26^0 = n1
    // then get hash for the first needle.length() from haystack
    // then do a rolling hash and compare
    // rolling hash example: abc -> bcd: (n1 - 0*26^2) * 26 + 3*26^0
}
