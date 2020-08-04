package problems.linkedlist;

import dataStructures.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 */
public class PalindromeLinkedList {
    // O(N), O(1)
    // we are modifying the input, which is in general bad practice because it could be used somewhere else outside of this function
    // so we restored it back
    public boolean isPalindrome(ListNode head) {
        // first find the middle point
        // reverse in place from mid to end
        // compare elements from first to mid

        if (head == null) {
            return true;
        }

        ListNode firstHalfEnd = findMidPoint(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // determine if palindrome
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;

        boolean result = true;
        while (result && p2  != null) {
            if (p1.val != p2.val) {
                result = false; // we can return here, but we want to restore input because we modified it
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // restore the list
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode findMidPoint(ListNode node) {
        // use 2 pointer approach
        ListNode slow = node;
        ListNode fast = node.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}