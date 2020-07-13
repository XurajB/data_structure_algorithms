package problems.linkedlist;

import java.util.Stack;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
 * which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
 */
public class FlattenMultilevelDll {
    // iterative
    // O(N)
    private Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        // we visit node, then its child, then its left. which is similar to a pre order traversal
        Stack<Node> stack = new Stack<>();
        Node prev = null;

        stack.push(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (prev != null) {
                prev.next = cur;
                cur.prev = prev;
                prev.child = null;
            }
            if (cur.next != null) {
                stack.push(cur.next);
            }
            if (cur.child != null) { // so we can pop first
                stack.push(cur.child);
            }
            prev = cur;
        }

        return head;
    }

    // recursive
    // O(N)
    private Node end = null;
    private Node flatten2(Node head) {
        if (head == null) {
            return end;
        }
        // 0. head -> flatten(head.child) -> flatten(head.next) --> end
        // 1. flatten(head.next) -> end
        end = flatten2(head.next);
        // 2. head -> flatten(head.child)
        head.next = flatten2(head.child);
        // 3. flatten(head.child) -> flatten(head.next)
        if (head.next != null) {
            head.next.prev = head;
        }
        head.child = null;
        return head;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
