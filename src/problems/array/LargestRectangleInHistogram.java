package problems.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }

    // O(N^2), O(1)
    // max area = max of  min height so far * range
    private static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                min = Math.min(min, heights[j]);
                maxArea = Math.max(maxArea, min * (j - i + 1));
            }
        }
        return maxArea;
    }

    // O(N), O(N)
    private static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>(); // stack of index, to calculate width
        stack.push(-1); //to offset width calculation

        for (int i = 0; i < heights.length; i++) {
            // if we encounter smaller height that what we have on top of stack
            // we pop because we have smaller height so won;t have any overlap for larger area
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                maxArea = Math.max(maxArea, stack.pop() * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        // stack may not be empty
        while (stack.peek() != - 1) {
            maxArea = Math.max(maxArea, stack.pop() * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }
}
