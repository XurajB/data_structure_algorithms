package problems.queue;

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
        // We are using deque for sliding window
        // use deque so we can store indexes max element of that window in the front
        // and use the back to store elements less than that number
        int n = nums.length;
        if (n == 0) {
            return nums;
        }

        // insert from one side. and take out from the same side if that element is less than current
        // if size is equals to k, take out answer from other side
        // if size is > k. remove out of range number from other side
        LinkedList<Integer> dq = new LinkedList<>();
        int[] answer = new int[n-k+1];

        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peek() < i-k+1) {
                dq.poll(); // take out the first element inserted, which was the max for last window
            }
            // if current element is more than the one we inserted before, then throw the last one. until we find something smaller
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            // we insert index so we know the window size
            dq.offerLast(i);
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
