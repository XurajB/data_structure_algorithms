package leetcode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode first = l1;
        ListNode second = l2;
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                node.next = first;
                first = first.next;
            } else {
                node.next = second;
                second = second.next;
            }
        }
        node.next = first == null ? second : first;
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

