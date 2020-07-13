package problems.string;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 */
public class EditDistance {
    public static void main(String[] args) {
        String word1 = "a";
        String word2 = "ba";
        System.out.println(minDistance(word1, word2));
    }

    // this distance is also called Levenshtein distance - a string metric for measuring difference between two sequences
    // The Levenshtein distance is the distance between two words - which is the minimum number of single-character edits.
    // *  *
    // *  h
    // top right = replace operation, top left = insert, bottom right = delete, h = we are here
    // O(mn)
    private static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m+1][n+1];

        // first row
        for (int i = 0; i <=n; i++) {
            cost[0][i] = i; // if empty word1, we need as many inserts on word1 as num of chars to make word2
        }

        // first col
        for (int i = 0; i <= m; i++) {
            cost[i][0] = i; // if empty word2, we will need to do as many deletes on word1 as num of chars to make word2
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // no operation. same amount of distance as if we removed both chars
                    cost[i][j] = cost[i-1][j-1];
                } else {
                    int replace = cost[i-1][j-1];
                    int delete = cost[i-1][j];
                    int insert = cost[i][j-1];
                    cost[i][j] = Math.min(replace, Math.min(delete, insert)) + 1;
                }
            }
        }

        return cost[m][n];
    }
}
