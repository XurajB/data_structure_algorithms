package problems.array;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 */
public class MinimumDominoRotation {
    public static void main(String[] args) {
        int[] A = {2,1,2,4,2,2};
        int[] B = {5,2,6,2,3,2};
        System.out.println(minDominoRotations(A, B));
    }

    /*
    We need the whole row to match. Therefore, if one tile does not match, we cannot have a matching row. We could arbitrarily pick any tile, but we pick the 0th tile since it always exists.
    In other words, one side of the first tile MUST match at least one side of every single one of the other tiles, or we cannot have an equal row.
    There could be three situations:
    1. One could make all elements of A row or B row to be same and equal to A[i]
    2. One could make all elements of A row or B row to be same and equal to B[i]
    3. It is impossible to make all elements of A row or B row to have same A[i] or B[i]
     */
    private static int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        int rotations = check(A[0], A, B);
        if (rotations != -1) {
            return rotations;
        }
        return check(B[0], A, B);
    }

    private static int check(int x, int[] A, int[] B) {
        int n = A.length;
        int rotationA = 0;
        int rotationB = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] != x && B[i] != x) {
                return -1;
            } else if (A[i] != x) {
                rotationA++;
            } else if (B[i] != x) {
                rotationB++;
            }
        }

        return Math.min(rotationA, rotationB);
    }
}
