package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];
        int move = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) {
                    continue;
                }
                visited[num] = true;
                if (num == n * n) {
                    return move;
                }
                for (int i = 1; i <= 6; i++) {
                    if (num + i > n * n) {
                        break;
                    }
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) next = value;
                    if (!visited[next]) {
                        queue.offer(next);
                    }
                }
            }
            move++;
        }
        return -1;
    }
    private int getBoardValue(int[][] grid, int num) {
        int n = grid.length;
        int rOld = (num - 1) / n;
        int r = n - 1 - rOld;
        int cOld = (num - 1) % n;
        int c = rOld % 2 == 0 ? cOld : n - 1 - cOld;
        return grid[r][c];
    }
}
