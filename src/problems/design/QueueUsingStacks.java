package problems.design;

import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 */
public class QueueUsingStacks {
    Stack<Integer> input;
    Stack<Integer> output;

    /**
     * it is easy to implement queue using just one stack, but there are many flaws - every push/pop is O(N) and not thread safe
     *
     * we maintain two stack - one for read and one for write,
     * that way we have thread safety and amortized complexity is O(1) - in worst case when output is empty, the pop is O(N) but when non empty it is O(1)
     * Amortized analysis gives the average performance (over time) of each operation in the worst case.
     * The basic idea is that a worst case operation can alter the state in such a way that the worst case cannot occur again for a long time, thus amortizing its cost.
     *
     * when we need to push - we push to input (write) stack. we only move all of input to output if output is empty otherwise we use output stack for all our reads
     */

    /** Initialize your data structure here. */
    public QueueUsingStacks() {
        input = new Stack<>(); // write
        output = new Stack<>(); // read
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // we can either reuse peek() or implement same logic here
        peek();
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
