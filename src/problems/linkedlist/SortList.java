package problems.linkedlist;

import dataStructures.ListNode;
// sort the given list
public class SortList {
    // insertion sort
    // O(N^2), O(1)
    public ListNode sortList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            ListNode min = node;
            ListNode temp = node.next;
            while (temp != null) {
                if (temp.val < min.val) {
                    min = temp;
                }
                temp = temp.next;
            }
            int p = min.val;
            min.val = node.val;
            node.val = p;

            node = node.next;
        }
        return head;
    }

    ///////////
    //
    // Merge sort, O(NLogN), O(N)
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // step 1: cut the list to two halves
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2, sort each half
        ListNode l1 = sortList2(head);
        ListNode l2 = sortList2(slow);

        // step 3, merge
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1);
        ListNode node = ans;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ans.next = l1;
                l1 = l1.next;
            } else {
                ans.next = l2;
                l2 = l2.next;
            }
            ans = ans.next;
        }

        if (l1 != null) {
            ans.next = l1;
        }
        if (l2 != null) {
            ans.next = l2;
        }
        return node.next;
    }
}
