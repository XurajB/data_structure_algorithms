package problems.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 */
public class WallsAndGate {
    // BFS
    // O(mn)
    // Instead of doing BFS each time we see a gate and keeping minimum distance, we can do all at once.
    // BFS will guarantee shortest distance d before searching rooms of distance d+1
    // The distance to an empty room must be shortest. So only check rooms that are empty
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int[][] DIRS = {{0,1},{1,0},{-1,0},{0,-1}};
    private static void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }

    // -----------------
    // DFS
    // O(mn)^2
    private static void wallsAndGates2(int[][] rooms) {
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == 0) dfs(rooms, i, j, 0);
            }
        }
    }

    private static void dfs(int[][] rooms, int i, int j, int d) {
        if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) return;
        rooms[i][j] = d;
        dfs(rooms, i - 1, j, d + 1);
        dfs(rooms, i, j - 1, d + 1);
        dfs(rooms, i + 1, j, d + 1);
        dfs(rooms, i, j + 1, d + 1);
    }
}
