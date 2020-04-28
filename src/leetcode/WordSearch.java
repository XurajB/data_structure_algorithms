package leetcode;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(exist(board, "ABCCED"));
    }

    // O(M*N*4^L), M*N = board, L = length of word. Space O(L)
    private static boolean exist(char[][] board, String word) {
        char c = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == c && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(index) != board[i][j]) {
            return false;
        }
        board[i][j] = '*';
        boolean result = dfs(board, i+1, j, word, index+1) ||
                dfs(board, i, j+1, word, index+1) ||
                dfs(board, i-1, j, word, index+1) ||
                dfs(board, i, j-1, word, index+1);
        board[i][j] = word.charAt(index);

        return result;
    }
}
