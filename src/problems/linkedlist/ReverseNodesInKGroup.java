package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 */
public class ReverseNodesInKGroup {
    // O(nk)
    public ListNode reverseKGroups(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode begin = dummy; // begin of next reverse

        int i = 0;

        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }

    // reverse and reassign begin and end
    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode temp;
        ListNode prev = begin;
        ListNode first = curr;

        while (curr != end) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        begin.next = prev;
        first.next = curr;
        return first;
    }
}
