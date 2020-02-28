package interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * https://www.interviewbit.com/problems/rotate-matrix/
 */
public class RotateMatrix {
    public static void main(String[] args) {
        // rotation can be done by first transposing the matrix
        // and reversing elements of each row
        List<Integer> row1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(4, 5, 6));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(7, 8, 9));
        ArrayList<List<Integer>> matrix = new ArrayList<>();
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        System.out.println(matrix);
        // first transpose matrix
        transpose(matrix);
        System.out.println(matrix);
        // then reverse elements of each row
        reverseRows(matrix);
        System.out.println(matrix);
    }

    // Time: O(N), n is the total element
    private static void transpose(ArrayList<List<Integer>> matrix) {
        int size = matrix.size();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                swap(matrix, i, j);
            }
        }
    }

    private static void reverseRows(ArrayList<List<Integer>> matrix) {
        int size = matrix.size();
        for (List<Integer> integers : matrix) {
            int left = 0;
            int right = size - 1;
            while (left < right) {
                swap(integers, left, right);
                left++;
                right--;
            }
        }
    }

    private static void swap(ArrayList<List<Integer>> matrix, int i, int j) {
        int temp = matrix.get(i).get(j);
        matrix.get(i).set(j, matrix.get(j).get(i));
        matrix.get(j).set(i, temp);
    }

    private static void swap(List<Integer> row, int i, int j) {
        int temp = row.get(i);
        row.set(i, row.get(j));
        row.set(j, temp);
    }
}