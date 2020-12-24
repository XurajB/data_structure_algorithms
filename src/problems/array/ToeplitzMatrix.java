package problems.array;

/**
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 */
public class ToeplitzMatrix {
    // O(mn)
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0 && j > 0 && matrix[i][j] != matrix[i-1][j-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // dfs
    // O(mn)
    public boolean isToeplitzMatrix2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!dfs(matrix, i, j, matrix[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] matrix, int i, int j, int num) {
        if (i >= matrix.length || j >= matrix[0].length || matrix[i][j] == -1) {
            return true;
        }

        boolean ans = dfs(matrix, i+1, j+1, num) && matrix[i][j] == num;
        matrix[i][j] = -1;
        return ans;
    }
}
