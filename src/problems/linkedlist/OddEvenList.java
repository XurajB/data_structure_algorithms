package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenList {
    // O(N), O(1)
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }

        // keep odd and even lists, append even at the end of odd
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null & even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
