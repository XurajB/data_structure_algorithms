package problems.bfsdfs;

import java.util.Arrays;

/**
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8')
 * represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
 * return the board after revealing this position according to the following rules:
 *
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 */
public class Minesweeper {
    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3,0};
        board = updateBoard(board, click);

        for (char[] row: board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // O(m*n)
    private static char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }

        // if click is bomb, mark x and return
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        dfs(board, x, y);
        return board;
    }

    static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}};
    private static void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') {
            return;
        }
        // get number of adjacent bombs
        int bombs = getNumberOfBombs(board, x, y);
        if (bombs == 0) {
            // we mark as B (blank) and search its neighbours
            board[x][y] = 'B';
            for (int[] dir: dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                dfs(board, nx, ny);
            }
        } else {
            board[x][y] = (char) (bombs+'0');
        }
    }

    private static int getNumberOfBombs(char[][] board, int x, int y) {
        int num = 0;
        for (int[] dir: dirs) {
            int newx = x + dir[0];
            int newy = y + dir[1];
            if (newx < 0 || newx >= board.length || newy < 0 || newy >= board[0].length) {
                continue;
            }
            if (board[newx][newy] == 'M' || board[newx][newy] == 'X') {
                num++;
            }
        }
        return num;
    }
}
