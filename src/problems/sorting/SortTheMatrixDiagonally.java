package problems.sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end.
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 */
public class SortTheMatrixDiagonally {
    /// using input
    ///
    // O(m*n log(min(m,n))
    // min(m,n)
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // sort lower left
        // 0,0 -> 1,1 2,2
        // 1,0 -> 2,1 3,2..
        for (int i = 0; i < m; i++) {
            sortDiagonal(mat, i, 0);
        }
        // sort upper right
        for (int j = 0; j < n; j++) {
            sortDiagonal(mat, 0, j);
        }
        return mat;
    }

    private void sortDiagonal(int[][] mat, int i, int j) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int m = mat.length;
        int n = mat[0].length;
        // get all the elements
        while (i < m && j < n) {
            pq.offer(mat[i][j]);
            i++;
            j++;
        }
        // insert from the bottom
        while (i > 0 && j > 0) {
            --i;
            --j;
            mat[i][j] = pq.poll();
        }
    }

    ////////////////////////////////////
    // O(m*n log(min(m,n))
    // O(m*n)
    public int[][] diagonalSort2(int[][] mat) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>(); // sort each value or pq
        // fill the hashmap using index i-j for diagonal
        int m = mat.length;
        int n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!map.containsKey(i-j)) {
                    map.put(i-j, new PriorityQueue<>());
                }
                map.get(i-j).add(mat[i][j]);
            }
        }

        // output
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(i-j).poll();
            }
        }

        return mat;
    }
}
