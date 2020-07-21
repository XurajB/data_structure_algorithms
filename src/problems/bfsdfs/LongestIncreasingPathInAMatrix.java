package problems.bfsdfs;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 */
public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(longestIncreasingPath(matrix));
    }

    // do dfs from every cell
    // the key is the cache the current distance to prevent duplicate traversal, it is highly likely that we will revisit same cells
    // O(m*n), each cell will only be calculated only once and each edge is only visited once, because of caching. otherwise it would be 4^(m+n) because there are 4 ways for each cell
    // O(mn)
    private static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];

        int max = 1; // one cell length is 1
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int length = dfs(matrix, cache, i, j);
                max = Math.max(max, length);
            }
        }
        return max;
    }

    private static int[][] dirs = new int[][] {{0,1},{1,0},{-1,0},{0,-1}};
    private static int dfs(int[][] matrix, int[][] cache, int x, int y) {
        if (cache[x][y] != 0) {
            return cache[x][y];
        }
        int max = 1;
        for (int[] dir: dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            // check if out of bound and next element which needs to be higher than current
            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[nx][ny] > matrix[x][y]) {
                int len = 1 + dfs(matrix, cache, nx, ny);
                max = Math.max(max, len);
            }
        }
        cache[x][y] = max;
        return max;
    }
}