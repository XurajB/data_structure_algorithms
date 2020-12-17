package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating direction each row.
 * Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
 */
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1]; // 1 based
        int move = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
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
                    // only take board value if it is not -1, otherwise use next to move forward
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
    // we need board value because at num, it can be a snake or ladder
    // need to convert num to row and col to get board value
    private int getBoardValue(int[][] grid, int num) {
        int n = grid.length;
        int rOld = (num - 1) / n;
        int r = n - 1 - rOld; // count from bottom
        int cOld = (num - 1) % n;
        int c = rOld % 2 == 0 ? cOld : n - 1 - cOld; // count from bottom, odd row is rtl
        return grid[r][c];
    }
}
