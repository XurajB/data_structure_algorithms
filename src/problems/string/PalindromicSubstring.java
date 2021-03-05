package problems.string;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 */
public class PalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }

    // O(N^2)
    private static int countSubstrings(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += countFromCenter(s, i, i); // odd length
            ans += countFromCenter(s, i, i+1); // even length
        }
        return ans;
    }

    private static int countFromCenter(String s, int i, int j) {
        int ans = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
            i--;
            j++;
            ans++;
        }
        return ans;
    }
}
