package problems.dynamic;

/**
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate,
 * for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3)
 * and the letter Z is located at coordinate (4,1).
 * Given the string word, return the minimum total distance to type such string using only two fingers.
 * The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 * Note that the initial positions of your two fingers are considered free so don't count towards your total distance,
 * also your two fingers do not have to start at the first letter or the first two letters.
 */
public class MinimumDistanceToTypeAWord {
    public static void main(String[] args) {
        System.out.println(minimumDistance("CAKE"));
    }

    // dfs
    private static int minimumDistance2(String word) {
        return minDist2(word, 0, null, null);
    }

    // 2^N
    private static int minDist2(String word, int index, Character c1, Character c2) {
        if (index >= word.length()) {
            return 0;
        }
        int dist1 = getDistance(c1, word.charAt(index)) + minDist2(word, index + 1, word.charAt(index), c2);
        int dist2 = getDistance(c2, word.charAt(index)) + minDist2(word, index + 1, word.charAt(index), c1);

        return Math.min(dist1, dist2);
    }

    ////////////////////
    // with dp

    static int[][][] dp = new int[27][27][300]; // 27 states for each finger (26 letters + 1 initial hover), 300 max word size. or use word.length()
    // dfs with memoization
    private static int minimumDistance(String word) {
        return minDist(word, 0, null, null);
    }

    // O(n * 27 ^ m), n = word length, m = number of fingers (2 in our case)
    // c1 and c2 are prev characters
    private static int minDist(String word, int index, Character c1, Character c2) {
        if (index >= word.length()) {
            return 0;
        }
        int idx1 = c1 == null ? 0 : c1 - 'A' + 1; // +1 because 27 length
        int idx2 = c2 == null ? 0 : c2 - 'A' + 1;

        // we have two choices: type the next character using either left or right index finger.
        // So we run DFS to find the min cost.
        // which ever distance is shortest from left or right hand
        if (dp[idx1][idx2][index] == 0) {

            int dist1 = getDistance(c1, word.charAt(index)) + minDist(word, index + 1, word.charAt(index), c2);
            int dist2 = getDistance(c2, word.charAt(index)) + minDist(word, index + 1, word.charAt(index), c1);

            dp[idx1][idx2][index] = Math.min(dist1, dist2);
        }

        return dp[idx1][idx2][index];
    }

    private static int getDistance(Character c1, Character c2) {
        if (c1 == null || c2 == null) {
            return 0;
        }
        int id1 = c1 - 'A'; // index of this character
        int id2 = c2 - 'A';

        int x1 = id1/6; // there are 6 cols. we are dividing our keyboard by 6 cols
        int y1 = id1%6;
        int x2 = id2/6;
        int y2 = id2%6;

        // calculate distance
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
