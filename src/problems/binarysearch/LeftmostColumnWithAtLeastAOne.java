package problems.binarysearch;

import java.util.List;

/**
 * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.
 *
 * You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.
 */
public class LeftmostColumnWithAtLeastAOne {
    interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }

    // R + C
    // if C is too large, RLogC solution might be preferable
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        // we start from top right
        // if 0, go down.. if 1 go left
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);

        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                col--;
            } else {
                row++;
            }
        }

        if (col == cols - 1) {
            return -1;
        }
        return col + 1;
    }

    // RLogC
    public int leftMostColumnWithOne1(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int smallestIndex = cols;
        // row sorted means, binary search on columns: 0 0 0 1 1 1
        for (int row = 0; row < rows; row++) {
            // Binary Search for the first 1 in the row.
            int lo = 0;
            int hi = cols - 1;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (binaryMatrix.get(row, mid) == 0) {
                    lo = mid + 1;
                }
                else {
                    hi = mid;
                }
                // If the last element in the search space is a 1, then this row
                // contained a 1.
                if (binaryMatrix.get(row, lo) == 1) {
                    smallestIndex = Math.min(smallestIndex, lo);
                }
            }
        }
        // If smallest_index is still set to cols, then there were no 1's in
        // the grid.
        return smallestIndex == cols ? -1 : smallestIndex;
    }
}
