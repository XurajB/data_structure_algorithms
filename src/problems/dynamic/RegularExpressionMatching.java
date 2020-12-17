package problems.dynamic;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        isMatch("abc", "a.c");
    }

    // O(m*n), O(m*n)
    private static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+ 1][n + 1];
        dp[0][0] = true; // empty s and p, matches so true
        // initialize first row
        // 2. dp[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
        // 3. dp[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex :P,
        // and for this case we need to check manually:
        // as we can see, the length of pattern should be even && the character at the even position should be *,
        // thus for odd length, dp[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2.
        // and notice that the length of repeat sub-pattern #* is only 2, we can just make use of dp[0][j - 2] rather than scanning j length each time
        // for checking if it matches #*#*#*#*.

        // empty s, non empty p
        // fill first row
        for (int i = 2; i <= n; i += 2) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), dp[i][j] = dp[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', dp[i][j] = dp[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. dp[i][j] = dp[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)ed
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then dp[i][j] = dp[i][j - 2]
        //      2.2 if counted as one, then dp[i][j] = dp[i - 1][j - 2]
        //      2.3 if counted as multiple, then dp[i][j] = dp[i - 1][j]
        for (int i = 1; i <= m; i++) {
            char curS = s.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char curP = p.charAt(j - 1);
                // case 1
                if (curS == curP || curP == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (curP == '*') {
                    // case 2
                    char preCurP = p.charAt(j-2);
                    if (preCurP != '.' && preCurP != curS) {
                        // check if we can assume 0 number of preceding element
                        // imagine #* is not there
                        dp[i][j] = dp[i][j-2]; // we can do j-2 when we start j=1, because we have valid input (* has a char in front)
                    } else {
                        dp[i][j] = (dp[i][j-2] || dp[i-1][j-2] || dp[i-1][j]); // empty case, one, multiple
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}
