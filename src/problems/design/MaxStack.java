package problems.design;

import java.util.Stack;

/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * https://leetcode.com/problems/max-stack/
 */
public class MaxStack {
    Stack<int[]> stack = new Stack<>();

    // all operations except popMax is O(1), popMax is O(n)
    // space: O(n)
    // two stack way might be less complicated

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[] {x, x});
            return;
        }
        int currentMax = stack.peek()[1];
        stack.push(new int[] {x, Math.max(currentMax, x)});
    }

    public int pop() {
        return stack.pop()[0];
    }

    public int top() {
        return stack.peek()[0];
    }

    public int peekMax() {
        return stack.peek()[1];
    }

    public int popMax() {
        int max = peekMax();
        Stack<int[]> buffer = new Stack<>();

        while (top() != max) {
            buffer.push(stack.pop());
        }
        pop();
        while (!buffer.isEmpty()) {
            push(buffer.pop()[0]);
        }
        return max;
    }
}
