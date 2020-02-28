package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        // implement
    }

    // Time: O(n*k), O(1)
    public ListNode mergeKLists(ListNode[] lists) {
        // one by one
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode current = merge(lists[0], lists[1]);
        for (int i = 2; i < lists.length; i++) {
            current = merge(current, lists[i]);
        }

        return current;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode current = new ListNode(-1);
        ListNode temp = current;
        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (l1 == null) {
            temp.next = l2;
        }
        if (l2 == null) {
            temp.next = l1;
        }

        return current.next;
    }

    // nLogn (logn for pq offer and poll), n for total integers
    // space - n for pq
    public ListNode mergeKLists2(ListNode[] lists) {

        Comparator<ListNode> comp = (l1, l2) -> l1.val - l2.val;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(comp);

        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        ListNode node = new ListNode(-1);
        ListNode temp = node;

        while (!pq.isEmpty()) {
            temp.next = pq.poll();
            temp = temp.next;
            ListNode next = temp.next;

            if (next != null) {
                pq.offer(next);
            }
        }
        return node.next;
    }

}
