package problems.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * https://www.interviewbit.com/problems/add-two-numbers-as-lists/
 */
public class AddTwoNumbersAsLists {
    class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

    public static void main(String[] args) {
        //
    }

    // O(max(l1, l2)), O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1);
        ListNode node = ans;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = (l1 == null) ? 0 : l1.val;
            int num2 = (l2 == null) ? 0 : l2.val;

            int sum = num1 + num2 + carry;
            node.next = new ListNode(sum%10);
            carry = sum / 10;

            node = node.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            node.next = new ListNode(carry);
        }

        return ans.next;
    }
}
