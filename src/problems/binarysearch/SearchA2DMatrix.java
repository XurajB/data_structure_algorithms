package problems.binarysearch;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * https://leetcode.com/problems/search-a-2d-matrix/
 */
public class SearchA2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        System.out.println(searchMatrix(matrix, 30));
    }

    // O(log(m*n)), O(1)
    private static boolean searchMatrix(int[][] matrix, int target) {
        // treat the matrix as a 1d array since they are sorted from left to right on all rows
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midElem = matrix[mid / n][mid % n];
            if (midElem == target) {
                return true;
            }
            if (target < midElem){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
