package problems.bfsdfs;

/**
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.
 * Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 */
public class SimilarStringGroups {
    public static void main(String[] args) {

    }

    // similar to num of island
    // O(N^2 * k)
    private static int numSimilarGroups(String[] A) {
        if (A.length < 2) {
            return A.length;
        }

        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null) {
                continue; // visited
            }
            String s = A[i];
            A[i] = null; // visited
            count++;
            dfs(A, s);
        }

        return count;
    }

    private static void dfs(String[] A, String s) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null) {
                continue;
            }
            if (isSimilar(A[i], s)) { // same group
                String s2 = A[i];
                A[i] = null; // mark null ans visited
                dfs(A, s2);
            }
        }
    }

    private static boolean isSimilar(String s1, String s2) {
        // swap two chars to convert to another string
        // so two chars need to be different. same string is also valid
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            if (diff > 2) {
                break; // no need to continue
            }
        }
        return diff <= 2;
    }
}
