package leetcode;

/**
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * https://leetcode.com/problems/design-tic-tac-toe/
 */
public class TicTacToe {
    // store sums of rows, cols and diagonals
    int[] rows;
    int[] cols;
    int diagonal = 0;
    int antiDiagonal = 0;
    int size;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        size = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    
    // O(1)
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;

        // add toAdd to both rows and columns
        rows[row] += toAdd;
        cols[col] += toAdd;

        if (row == col) {
            diagonal += toAdd;
        }
        if (row == size - col - 1) {
            antiDiagonal += toAdd;
        }

        // if any row/col/diagonal sum is equal to size, that player wins
        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size || Math.abs(antiDiagonal) == size) {
            return player;
        }
        return 0;
    }
}
