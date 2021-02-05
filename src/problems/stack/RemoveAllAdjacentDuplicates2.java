package problems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing
 * them causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 */
public class RemoveAllAdjacentDuplicates2 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
    }
    // O(N), O(N)
    private static String removeDuplicates(String s, int k) {
        Deque<Node> stack = new ArrayDeque<>(); // note use push and pop, if want to use as stack.
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!stack.isEmpty()) {
                if (ch == stack.peek().ch) {
                    stack.peek().count++;
                    if (stack.peek().count == k) {
                        stack.pop();
                    }
                } else {
                    stack.push(new Node(ch, 1));
                }
            } else {
                stack.push(new Node(ch, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            while (node.count-- > 0) {
                sb.append(node.ch);
            }
        }
        return sb.toString();
    }

    static class Node {
        char ch;
        int count;
        Node (char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
}
