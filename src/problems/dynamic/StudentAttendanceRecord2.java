package problems.dynamic;

/**
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable.
 * The answer may be very large, return it after mod 109 + 7.
 * A student attendance record is a string that only contains the following three characters:
 *
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 */
public class StudentAttendanceRecord2 {
    public static void main(String[] args) {
        System.out.println(checkRecord(4));
    }

    // O(N)
    static final int mod = (int) (1e9+7);
    private static int checkRecord(int n) {
        long[] P = new long[n+1]; // end with P w/o A
        long[] L = new long[n+1]; // end with L w/o A

        P[0] = P[1] = L[1] = 1;
        for (int i = 2; i <= n; i++) {
            P[i] = (P[i-1] + L[i-1]) % mod; // P is valid either last record was P or L
            L[i] = (P[i-1] + P[i-2]) % mod; // L is valid if last record was P or i-2 is P (we cannot have 3 consecutive Ls)
        }
        // result so far
        long ans = (P[n] + L[n]) % mod;

        // insert A
        // we cannot have more than 1 A
        for (int i = 0; i < n; i++) {
            long s = (P[i] + L[i]) % mod * (P[n-i-1] + L[n-i-1]) % mod;
            ans = ans + s;
        }

        return (int) ans;
    }

    //TLE
    private static int checkRecord2(int n) {
        dfs("", n);
        return count;
    }
    static int count = 0;

    private static void dfs(String s, int n) {
        if (n < 0) {
            return;
        }
        if (n == 0 && checkValid(s)) {
            count = (count + 1) % mod;
            return;
        }

        dfs(s + "A", n - 1);
        dfs(s + "P", n - 1);
        dfs(s + "L", n - 1);
    }

    private static boolean checkValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                count++;
            }
        }
        // not 2 As, or more than 2 consecutive Ls
        return s.length() > 0 && count < 2 && !s.contains("LLL");
    }
}
