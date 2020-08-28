package problems.array;

/**
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares (" ").
 * The first player always places "X" characters, while the second player always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 */
public class ValidTicTacToeState {
    public static void main(String[] args) {
        String[] board = {"XOX", " X ", "   "};
        System.out.println(validTicTacToe(board));
    }

    // O(1)
    // cases that leads to invalid states
    // 1. there are less X than O (since x starts first), or #x - #o > 1
    // 2. either x or o has more than 1 winning patterns
    // 3. if x wins, then o cannot win and there must be more x than o
    // 4. if o wins, then x cannot win, there much be same number of x as o
    private static boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;

        for (String row: board) {
            for (char cell :row.toCharArray()) {
                if (cell == 'X') {
                    countX++;
                } else if (cell == 'O') {
                    countO++;
                }
            }
        }

        // case 1
        if (countX < countO || countX - countO > 1) {
            return false;
        }
        // case 2
        int xWon = countWon(board, 'X');
        int oWon = countWon(board, 'O');
        if (xWon > 1 || oWon > 1) {
            return false;
        }

        // case 3
        if (xWon == 1) {
            return oWon == 0 && countX > countO;
        }

        // case 4
        if (oWon == 1) {
            return xWon == 0 && countO == countX;
        }

        // all other cases are valid
        return true;
    }

    private static int countWon(String[] board, char p) {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            // vertical
            if (board[i].charAt(0) == p && board[i].charAt(1) == p && board[i].charAt(2) == p) {
                count++;
            }
            // horizontal
            if (board[0].charAt(i) == p && board[1].charAt(i) == p && board[2].charAt(i) == p) {
                count++;
            }
        }

        // diagonal
        if (board[1].charAt(1) == p) {
            if (board[0].charAt(0) == p && board[2].charAt(2) == p) count++;
            if (board[0].charAt(2) == p && board[2].charAt(0) == p) count++;
        }

        return count;
    }
}
