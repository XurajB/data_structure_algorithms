package problems.graph;

import java.util.PriorityQueue;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid.
 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 */
public class DungeonGame {
    public static void main(String[] args) {
        int[][] dungeon = new int[][] {
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };
        System.out.println(calculateMinimumHP(dungeon));
    }

    // O(m*n)
    private static int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
            return 0;
        }

        int m = dungeon.length;
        int n = dungeon[0].length;
        // dp[i][j] represents the health when I reach dungeon[i][j]
        int[][] dp = new int[m][n];
        // we start from bottom right
        // initialization:
        // dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        // or dp[m - 1][n - 1] = dungeon[i][j] >= 0? 1 : -dungeon[i][j] + 1;
        // induction rule:
        // dp[i][n - 1] = max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1)
        // dp[m - 1][j] = max(dp[m - 1][j + 1] - dungeon[m - 1][j], 1)
        // dp[i][j] = min(max(dp[i][j + 1] - dungeon[i][j], 1), max(dp[i + 1][j] - dungeon[i][j], 1))
        for (int i = m-1; i >= 0; i --) {
            for (int j = n-1; j >=0; j--) {
                // first case - we are at beginning. initialize
                if (i == m - 1 && j == n - 1) {
                    // we need at least 1 health to be alive
                    dp[i][j] = Math.max(1, 1 - dungeon[i][j]);
                } else if (i == m - 1) { // last row
                    dp[m-1][j] = Math.max(1, dp[m-1][j+1] - dungeon[m-1][j]);
                } else if (j == n - 1) { // last col
                    dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);
                } else {
                    // max bottom or right
                    dp[i][j] = Math.min(Math.max(1, dp[i][j+1] - dungeon[i][j]), Math.max(1, dp[i+1][j] - dungeon[i][j]));
                }
            }
        }

        return dp[0][0];
    }
}
