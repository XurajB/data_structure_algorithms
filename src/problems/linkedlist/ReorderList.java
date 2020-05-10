package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * Example: 1->2->3->4->5 to 1->5->2->4->3
 */
public class ReorderList {
    // O(N), O(1)
    private static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 3 steps
        // two runners to find the middle
        // reverse the middle to last part
        // merge two lists

        // step 1
        // find pre middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the right part of the list
        // 1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode prev = null;
        ListNode current = slow;
        ListNode temp = null;
        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        // merge two parts one by one
        // 1->2->3 & 6->5->4 to 1->6->2->5->3->4
        ListNode first = head;
        ListNode second = prev;
        while (second.next != null) {
            temp = first.next;
            first.next = second;
            first = temp;

            temp = second.next;
            second.next = first;
            second = temp;
        }
    }
}
