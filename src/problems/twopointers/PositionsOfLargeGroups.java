package problems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.
 * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
 * Call a group large if it has 3 or more characters.
 * We would like the starting and ending positions of every large group.
 *
 * Follow up: #ExpressiveWords
 */
public class PositionsOfLargeGroups {
    public static void main(String[] args) {
        System.out.println(largeGroupPositions("abbxxxxzyy"));
    }

    // O(N), O(1)
    private static List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0;
        int j = 1;
        while (i < S.length() && j < S.length()) {
            while (j < S.length() && S.charAt(i) == S.charAt(j)) {
                j++;
            }
            if (j - i >= 3) {
                ans.add(Arrays.asList(i, j-1));
            }
            i = j;
            j = j + 1;
        }
        return ans;
    }
}
