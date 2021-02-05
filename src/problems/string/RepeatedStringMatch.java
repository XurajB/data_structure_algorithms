package problems.string;

/**
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​
 * to be a substring of a after repeating it, return -1.
 *
 * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".
 */
public class RepeatedStringMatch {
    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));
    }

    // O(B)
    // Another way is to use Rabin Karp for O(1) space
    private static int repeatedStringMatch(String A, String B) {
        if (A == null || B == null) {
            return  -1;
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }

        // we can use sb.contains(B) or use our own function
        if (sb.toString().contains(B)) {
            return count;
        }
        // for cases that have repeating from the middle
        // abcd, cdabcdab
        if (sb.append(A).toString().contains(B)) {
            count++;
            return count;
        }
        return -1;
    }
}
