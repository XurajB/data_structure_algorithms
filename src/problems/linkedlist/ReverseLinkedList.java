package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Reverse a single linked list
 */
public class ReverseLinkedList {
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }
}
