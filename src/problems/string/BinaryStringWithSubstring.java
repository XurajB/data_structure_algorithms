package problems.string;

/**
 * Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N,
 * return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.
 */
public class BinaryStringWithSubstring {
    public boolean queryString(String S, int N) {
        if (!S.contains("1")) {
            return false;
        }
        String next = "1";
        for (int i = 2; i <= N; i++) {
            next = addOne(next);
            if (!S.contains(next)) {
                return false;
            }
        }
        return true;
    }

    private String addOne(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '0') {
                sb.setCharAt(i, '1');
                return sb.toString();
            } else {
                sb.setCharAt(i, '0');
            }
        }
        return "1" + sb.toString();
    }
}
