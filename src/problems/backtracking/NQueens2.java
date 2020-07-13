package problems.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 *  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *  Given an integer n, return number of distinct solutions to the n-queens puzzle.
 *  Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */
public class NQueens2 {
    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }

    // O(N!), O(N)
    private static int totalNQueens(int n) {
        return totalNQueensHelper(0, 0, n);
    }

    // we don't actually need to place queens because we only are returning number of possibilities
    // we check if placing at this position violates rules or not. which is to detect if 2 positions sit on the same diagonal
    private static final Set<Integer> occupiedCols = new HashSet<>();
    private static final Set<Integer> occupiedDiag1 = new HashSet<>();
    private static final Set<Integer> occupiedDiag2 = new HashSet<>();
    private static int totalNQueensHelper(int row, int count, int n) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col)) {
                continue;
            }
            int diag1 = row - col;
            if (occupiedDiag1.contains(diag1)) {
                continue;
            }
            int diag2 = row + col;
            if (occupiedDiag2.contains(diag2)) {
                continue;
            }
            // see if we are in the last row, which means we covered the board
            if (row == n - 1) {
                count++;
            } else {
                occupiedCols.add(col);
                occupiedDiag1.add(diag1);
                occupiedDiag2.add(diag2);

                count = totalNQueensHelper(row + 1, count, n);

                // backtrack
                occupiedCols.remove(col);
                occupiedDiag1.remove(diag1);
                occupiedDiag2.remove(diag2);
            }
        }
        return count;
    }
}
