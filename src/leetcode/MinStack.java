package leetcode;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * https://leetcode.com/problems/min-stack/
 */
public class MinStack {

    // all methods: O(1), space: N
    static class MinStack1 {
        Stack<int[]> stack = new Stack<>();

        // min until we have x will always be the same
        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(new int[] {x, x});
                return;
            }
            int currentMin = stack.peek()[1];
            stack.push(new int[]{x, Math.min(x, currentMin)});
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.pop()[0];
        }

        public int getMin() {
            return stack.pop()[1];
        }
    }

    static class MinStack2 {
        Stack<Integer> minStack = new Stack<>();
        Stack<Integer> stack = new Stack<>();

        public void push(int x) {
            if (stack.isEmpty()) {
                minStack.push(x);
                stack.push(x);
                return;
            }

            int currentMin = minStack.peek();
            stack.push(x);
            minStack.push(Math.min(x, currentMin));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
