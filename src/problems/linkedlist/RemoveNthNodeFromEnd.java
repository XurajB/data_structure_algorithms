package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
 */
public class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        if (n == size) {
            head = head.next;
            return head;
        }
        node = head;
        for (int i = 0; i < (size - n - 1); i++) {
            node = node.next;
        }
        node.next = node.next.next;
        return head;
    }

    // different way
    // move first pointer to n from the front and move second pointer from first.
    // when first pointer hits null, second will be at n from the back
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode front = head;
        ListNode back = head;

        while(n-- > 0) {
            front = front.next;
        }

        if (front == null) {
            return head.next;
        }
        while (front.next != null) {
            front = front.next;
            back = back.next;
        }

        back.next = back.next.next;

        return head;
    }
}
