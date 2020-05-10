package problems.binarysearch;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchA2DMatrix2 {
    public static void main(String[] args) {

    }

    // we start from bottom left. if target is higher than current index, move right. if less move up.
    // O(r + c)
    // The key to the time complexity analysis is noticing that, on every iteration (during which we do not return true) either row or col is is decremented/incremented exactly once.
    // Because row can only be decremented mm times and col can only be incremented nn times before causing the while loop to terminate,
    // the loop cannot run for more than n+mn+m iterations.
    private static boolean searchMatrix(int[][] matrix, int target) {
        // start pointer in the bottom-left
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }

    // binary search on each row
    // time: nLog(n), space: 1
    private static boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            int[] row = matrix[i];
            if (hasTarget(row, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasTarget(int[] row, int target) {
        int start = 0;
        int end = row.length - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
