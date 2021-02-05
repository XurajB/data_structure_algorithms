package problems.array;

/**
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 */
public class BattleshipsInABoard {
    public static void main(String[] args) {

    }

    private static int countBattleships(char[][] board) {
        // if they are connected then we only count 1, only those that the first cell of the battleship
        // we define first cell as top-left, so we check for first cell only that do not have X on left and top
        int m = board.length;
        if (m == 0) {
            return 0;
        }
        int n = board[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i-1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j-1] == 'X') {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    // dfs
    //// if we find another X that is unvisited after dfs, that will be start of another battle ship
    private static int countBattleships2(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        int count = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if(board[i][j] == 'X' && !visited[i][j]) {
                    count++;
                    dfs(board, visited, i, j);
                }

        return count;
    }

    static void dfs(char[][] board, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length)
            return;
        if (visited[i][j] || board[i][j] != 'X')
            return;
        visited[i][j] = true;
        dfs(board, visited, i + 1, j);
        dfs(board, visited, i - 1, j);
        dfs(board, visited, i, j + 1);
        dfs(board, visited, i, j - 1);
    }
}
