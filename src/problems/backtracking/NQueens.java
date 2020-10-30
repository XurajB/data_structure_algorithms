package problems.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */
public class NQueens {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    // O(n^3 * n!)
    // we can improve to n! using memoization (TODO)
    private static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        if (n <= 0) {
            return res;
        }
        // build board
        char[][] board = new char[n][n]; // nxn board, n queens
        // initialize with .
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        // we solve col by col
        solve(board, 0, res);

        return res;
    }

    private static void solve(char[][] board, int col, List<List<String>> res) {
        if (col == board.length) {
            res.add(buildString(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            // check if it is valid
            if (isValid(board, i, col)){
                board[i][col] = 'Q';
                solve(board, col + 1, res); // solve next row
                board[i][col] = '.'; // backtrack
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <col; j++) {
                // check 4 things
                // 1. no conflict in col: which we true because we are doing it col by col
                // 2. no conflict in row: i == row
                // 3. 4. no diagonal conflicts: Math.abs(row - i) == Math.abs(col - j). if distance between the columns == distance between rows, they are in the same diagonal

                if(board[i][j] == 'Q' && (Math.abs(row - i) == Math.abs(col - j) || row == i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<String> buildString(char[][] board) {
        List<String> temp = new LinkedList<>();
        for (char[] row: board) {
            temp.add(new String(row));
        }
        return temp;
    }
}
