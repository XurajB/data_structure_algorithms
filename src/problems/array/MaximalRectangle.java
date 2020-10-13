package problems.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalRectangle(matrix));
    }

    // O(n^2)
    private static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int[] heights = new int[matrix[0].length]; // histogram of columns

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // update the state of this row's histogram using last row's histogram
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }

            // use the max area in histogram algorithm to calculate max area of this histogram
            maxArea = Math.max(maxArea, maxAreaInHistogram(heights));
        }
        return maxArea;
    }

    private static int maxAreaInHistogram(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {

        }
        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    ///////
    // same thing as above but combined
    private static int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        int[] histogram = new int[matrix[0].length]; // histogram of columns

        for (char[] chars : matrix) {

            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            for (int j = 0; j < chars.length; j++) {
                // update the state of this row's histogram using last row's histogram
                histogram[j] = chars[j] == '0' ? 0 : histogram[j] + 1;

                // use the max area in histogram algorithm to calculate max area of this histogram
                while (stack.peek() != -1 && histogram[stack.peek()] >= histogram[j]) {
                    maxArea = Math.max(maxArea, histogram[stack.pop()] * (j - stack.peek() - 1));
                }
                stack.push(j);
            }
        }
        return maxArea;
    }
}
