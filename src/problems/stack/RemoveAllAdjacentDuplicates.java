package problems.stack;

import java.util.Stack;

/**
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 * We repeatedly make duplicate removals on S until we no longer can.
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 */
public class RemoveAllAdjacentDuplicates {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbaca"));
    }

    // O(N), O(N)
    private static String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (!stack.isEmpty() && S.charAt(i) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(S.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    //// using sb as stack
    private static String removeDuplicates2(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (sb.length() > 0 && S.charAt(i) == sb.charAt(sb.length() - 1)) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
