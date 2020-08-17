package problems.slidingwindow;

/**
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }

    // O(N)
    private static int characterReplacement(String s, int k) {
        int[] map = new int[26];
        int start = 0;
        int end = 0;
        int maxCount = 0;
        int ans = 0;
        while (end < s.length()) {
            map[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, map[s.charAt(end) - 'A']);

            // if max character frequency + distance between start and end is greater than k
            // this means we have considered changing more than k charactres. So time to shrink window
            while (end - start + 1 - maxCount > k){
                map[s.charAt(start) - 'A']--;
                start ++;
            }

            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }
}
