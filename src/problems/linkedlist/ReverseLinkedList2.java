package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * Note: 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedList2 {
    // O(n)
    private ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = dummy.next;

        // first go to m
        int i = 1;
        while (i < m) {
            prev = curr;
            curr = curr.next;
            i++;
        }

        // reverse from m to n
        ListNode node = prev;
        while (i <= n) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            i++;
        }

        node.next.next = curr;
        node.next = prev;

        return dummy.next;
    }
}
