package problems.slidingwindow;

/**
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 */
public class LongestSubstringWithAtMost2Distinct {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
    }

    // O(N), O(1)
    private static int lengthOfLongestSubstringTwoDistinct(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int count = 0;

        int[] map = new int[128];
        while (end < s.length()) {
            char c = s.charAt(end);
            map[c]++;
            if (map[c] == 1) {
                count++;
            }
            while (count > 2) {
                char c2 = s.charAt(start);
                map[c2]--;
                if (map[c2] == 0) {
                    count--;
                }
                start++;
            }
            max = Math.max(max, end-start+1);
            end++;
        }
        return max;
    }
}
