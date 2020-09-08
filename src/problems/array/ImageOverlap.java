package problems.array;

/**
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.
 * After, the overlap of this translation is the number of positions that have a 1 in both images.
 * (Note also that a translation does not include any kind of rotation.)
 * What is the largest possible overlap?
 */
public class ImageOverlap {
    public static void main(String[] args) {
        int[][] A = {
                {1,1,0},
                {0,1,0},
                {0,1,0}};

        int[][] B = {
                {0,0,0},
                {0,1,1},
                {0,0,1}};
        System.out.println(largestOverlap(A, B));
    }

    private static int largestOverlap(int[][] A, int[][] B) {
        int maxOverlaps = 0;

        for (int yShift = 0; yShift < A.length; ++yShift)
            for (int xShift = 0; xShift < A.length; ++xShift) {
                // move one of the matrix up and left and vice versa.
                // (equivalent to move the other matrix down and right)
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, A, B));
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, B, A));
            }

        return maxOverlaps;
    }

    private static int shiftAndCount(int xShift, int yShift, int[][] M, int[][] R) {
        int count = 0;
        int rRow = 0;
        // count the cells of ones in the overlapping zone.
        for (int mRow = yShift; mRow < M.length; ++mRow) {
            int rCol = 0;
            for (int mCol = xShift; mCol < M.length; ++mCol) {
                if (M[mRow][mCol] == 1 && M[mRow][mCol] == R[rRow][rCol])
                    count += 1;
                rCol += 1;
            }
            rRow += 1;
        }
        return count;
    }
}
