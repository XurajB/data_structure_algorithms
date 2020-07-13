package problems.bfsdfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(matrix));
    }

    // O(r * c)
    private static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int r = matrix.length;
        int c = matrix[0].length;

        // visited map for each ocean
        boolean[][] pacific = new boolean[r][c];
        boolean[][] atlantic = new boolean[r][c];

        // queue for bfs for each
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();

        // vertical border
        // left is pacific, right is atlantic
        for (int i = 0; i < r; i++) {
            pQueue.offer(new int[] {i, 0});
            aQueue.offer(new int[] {i, c - 1});
            pacific[i][0] = true;
            atlantic[i][c-1] = true;
        }

        // horizontal border
        // top is pacific, bottom is atlantic
        for (int i = 0; i < c; i++) {
            pQueue.offer(new int[] {0, i});
            aQueue.offer(new int[] {r - 1, i});
            pacific[0][i] = true;
            atlantic[r-1][i] = true;
        }

        // bfs starting from each side
        // imagine water flowing from ocean to cell.
        // since water can only flow from high/equal cell to low cell, add the neighbour cell with height higher or equal to current to the queue and mark as visited
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);

        // now we have visited cells that have common visited area where water can flow into both sides
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private static void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int r = matrix.length;
        int c = matrix[0].length;

        int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir: dirs) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                // only visited area that have higher or equal height
                if (nextX >= 0 && nextX < r && nextY >= 0 && nextY < c
                        && !visited[nextX][nextY]
                        && matrix[nextX][nextY] >= matrix[cur[0]][cur[1]]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[] {nextX, nextY});
                }
            }
        }
    }

    ///////////////////////////////////////////////////////
    /// DFS solution
    // O(m * n)
    private static List<List<Integer>> pacificAtlantic2(int[][] matrix) {
        List<List<Integer>> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        for(int i=0; i<n; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m-1);
        }
        for (int i=0; i<m; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n-1, i);
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (pacific[i][j] && atlantic[i][j])
                    res.add(Arrays.asList(i, j));
        return res;
    }

    static int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    private static void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
        int n = matrix.length, m = matrix[0].length;
        if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;
        for (int[] d:dir){
            dfs(matrix, visited, matrix[x][y], x+d[0], y+d[1]);
        }
    }
}
