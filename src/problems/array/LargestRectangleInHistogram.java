package problems.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea2(heights));
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

        for (int i = 0; i < heights.length; i++) {
            // if we encounter smaller height than what we have on top of stack
            // we pop because we have smaller height so won;t have any overlap for larger area
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // example: 2,1,5,6,2,3: i:4, if stack.peek is 5. 5 * (4 - 1 + 1) //1 because 5 is popped
                int area = heights[stack.pop()] * (i - (stack.isEmpty() ? 0 : stack.peek() + 1));
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - (stack.isEmpty() ? 0 : stack.peek() + 1)));
        }
        return maxArea;
    }
}
