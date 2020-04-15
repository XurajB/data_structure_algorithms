package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow1(nums, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    private static int[] maxSlidingWindow(int[] nums, int k) {
        // use deque so we can store indexes max element of that window in the front
        // and use the back to store elements less than that number
        int n = nums.length;
        if (n == 0) {
            return nums;
        }

        LinkedList<Integer> dq = new LinkedList<>();
        int[] answer = new int[n-k+1];

        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peek() < i-k+1) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i-k+1 >= 0) {
                answer[i-k+1] = nums[dq.peek()];
            }
        }

        return answer;
    }

    // O(N*k), O(n-k+1)=O(1)
    private static int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[] {0};
        }
        int[] answer = new int[n-k+1];
        for (int i = 0; i < n-k+1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            answer[i] = max;
        }

        return answer;
    }
}
