package problems.array;

/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 */
public class GameOfLife {
    // O(m*n), O(1)
    public void gameOfLife(int[][] board) {

        int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int livecount = 0;
                for (int[] dir: dirs) {

                    int newi = i + dir[0];
                    int newj = j + dir[1];

                    if (newi < 0 || newj < 0 || newi >= board.length || newj >= board[0].length) {
                        continue;
                    }
                    // live is -1 or 1 because we are solving this in place
                    // we are using dummy cell value to signify previous state
                    // if we are allowed to use extra space, we can copy board to another 2d array and use that to compare
                    if (Math.abs(board[newi][newj]) == 1) {
                        livecount++;
                    }
                }

                // rule 1 and 3
                // denote -1 as dead and 2 as live
                if (board[i][j] == 1 && (livecount < 2 || livecount > 3)) {
                    board[i][j] = -1; // -1 means living before now dead. Since it happens simultaneously we need to know if it was living before
                }
                // rule 4
                if (board[i][j] == 0 && livecount == 3) {
                    board[i][j] = 2;
                }

            }
        }
        // repair the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
