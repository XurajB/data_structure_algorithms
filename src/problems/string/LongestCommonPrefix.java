package problems.string;

/**
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strings));

        // this can also be solved using Trie
        // same complexity, space is O(total chars)
        // Trie version: go down the root as long as it has only one child
    }

    // Time complexity: O(number of all chars), Space: O(1)
    private static String longestCommonPrefix(String[] strings) {
        String base = strings[0];

        for (int i = 0; i < base.length(); i++) {
            for (int j = 1; j < strings.length; j++) {
                if (strings[j].length() == i ||
                        strings[j].charAt(j) != base.charAt(j)) {
                    return base.substring(0, j);
                }
            }
        }

        return base;
    }
}
