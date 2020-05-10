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

    public ListNode addTwoNumbers(ListNode A, ListNode B) {
        Queue<Integer> s1 = new LinkedList<>();
        Queue<Integer> s2 = new LinkedList<>();
        while (A != null) {
            s1.add(A.val);
            A = A.next;
        }
        while (B != null) {
            s2.add(B.val);
            B = B.next;
        }
        ListNode answer = new ListNode(-1);
        ListNode current = answer;

        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int num1 = 0, num2 = 0;
            if (!s1.isEmpty()) {
                num1 = s1.remove();
            }
            if (!s2.isEmpty()) {
                num2 = s2.remove();
            }
            int sum = num1 + num2 + carry;

            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            current = current.next;
        }

        if (carry == 1) {
            current.next = new ListNode(1);
        }

        return answer.next;
    }
}
