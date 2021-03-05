package problems.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.
 * Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 */
public class SimilarStringGroups {
    public static void main(String[] args) {
        System.out.println(numSimilarGroups(new String[] {"tars","rats","arts","star"}));
    }

    // Union find
    // N^2 * W
    private static int numSimilarGroups(String[] A) {
        int[] parents = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            parents[i] = i;
        }

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isSimilar2(A[i], A[j])) {
                    // union
                    int x = find(parents, j);
                    int y = find(parents, i);

                    if (x != y) {
                        parents[x] = y;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == i) {
                result++;
            }
        }
        return result;
    }

    private static int find(int[] parents, int i) {
        if (parents[i] == i) {
            return parents[i];
        }
        parents[i] = find(parents, parents[i]);
        return parents[i];
    }

    ////
    // bfs
    // O(N^2 * W)
    public int numSimilarGroups2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[strs.length];
        Queue<String> queue = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            queue.add(strs[i]);
            ans++;
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                for (int j = 0; j < strs.length; j++) {
                    if (visited[j]) {
                        continue;
                    }
                    if (isSimilar(cur, strs[j])) {
                        visited[j] = true;
                        queue.offer(strs[j]);
                    }
                }
            }
        }
        return ans;
    }

    private boolean isSimilar(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count ++;
            }
        }
        return count <= 2;
    }

    // similar to num of island
    // O(N^2 * k)
    private static int numSimilarGroups3(String[] A) {
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
            if (isSimilar2(A[i], s)) { // same group
                String s2 = A[i];
                A[i] = null; // mark null ans visited
                dfs(A, s2);
            }
        }
    }

    private static boolean isSimilar2(String s1, String s2) {
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
