package problems.bfsdfs;

import java.util.PriorityQueue;

/**
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
 */
public class TrappingRainWater2 {
    public static void main(String[] args) {
        int[][] heights = {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };
        System.out.println(trapRainWater(heights));
    }

    private static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.height - b.height);
        boolean[][] visited = new boolean[m][n];

        // add the cells that are on the borders
        // top bottom
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m-1][i] = true;
            queue.offer(new Cell(0, i, heightMap[0][i]));
            queue.offer(new Cell(m-1, i, heightMap[m-1][i]));
        }

        // left right
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, n-1, heightMap[i][n-1]));
        }

        // from the boarders, pick the shortest cell and check its neighbours
        // if the neighbour is shorter, collect water it can trap and update its height with its height + water trapped
        int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell current = queue.poll();
                int x = current.row;
                int y = current.col;
                int height = current.height;
                for (int[] dir: dirs) {
                    int newx = x + dir[0];
                    int newy = y + dir[1];

                    if (newx >= 0 && newy >= 0 && newx < m && newy < n && !visited[newx][newy]) {
                        visited[newx][newy] = true;
                        ans += Math.max(0, height - heightMap[newx][newy]);
                        queue.offer(new Cell(newx, newy, Math.max(heightMap[newx][newy], height)));
                    }
                }
            }
        }

        return ans;
    }

    static class Cell {
        int row;
        int col;
        int height;
        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}
