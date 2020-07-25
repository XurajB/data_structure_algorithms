package problems.bfsdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. (any 0 connected to border are saved - escape in this game)
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. (everything else are captured)
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        //
    }

    // O(N)
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int r = board.length;
        int c = board[0].length;

        // idea is mark everything connected from boarders as can't capture
        // and mark everything else as captured. We only need to do dfs from boarder region so we can mark can't capture
        // everything else can be marked as X

        // first get a list of edges
        List<Pair> edges = new ArrayList<>(); // or List<int[]>
        // rows (left and right edges)
        for (int i = 0; i < r; i++) {
            edges.add(new Pair(i, 0));
            edges.add(new Pair(i, c-1));
        }
        // cols (top and bottom edges)
        for (int i = 0; i < c; i++) {
            edges.add(new Pair(0, i));
            edges.add(new Pair(r-1, i));
        }

        // do dfs on boarder items and mark all connected items to something else
        for (Pair pair: edges) {
            if (board[pair.x][pair.y] == 'O') {
                dfs(board, pair.x, pair.y);
            }
        }

        // at this point all border Os and their connection are marked E
        // we can turn all remaining O to X and E back to O
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'E'; // mark with something else
        dfs(board, x+1, y);
        dfs(board, x, y+1);
        dfs(board, x-1, y);
        dfs(board, x, y-1);
    }

    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
