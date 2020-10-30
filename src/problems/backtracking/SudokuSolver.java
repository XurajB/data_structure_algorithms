package problems.backtracking;

import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 */
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    // there are 9 possibilities in each cell, and n cells with .
    // O(9^N)
    private static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }

    // also called constrained programming. Only pick paths that are valid instead of doing a brute force to generate all possible ways (N^N)
    private static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    // do a trial from 1 to 9
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            // if valid put c in that cell
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            }
                            // other wise backtrack
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(char[][] board, int row, int col, char c) {
        // row by row
        for (int i = 0; i < 9; i++) {
            // check row
            if (board[row][i] == c) {
                return false;
            }
            // check col
            if (board[i][col] == c) {
                return false;
            }

            // check 3x3 block
            // this will give us start position of that block
            // 3,3 means row 3 and col 3 (start of 4th block)
            int blockRow = 3 * (row / 3);
            int blockCol = 3 * (col / 3);

            int indexR = blockRow + i / 3;
            int indexC = blockCol + i % 3;

            if (board[indexR][indexC] == c) {
                return false;
            }
        }
        return true;
    }
}
