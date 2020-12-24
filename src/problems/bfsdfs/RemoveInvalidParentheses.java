package problems.bfsdfs;

import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 * https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("(a)())()"));
    }

    // BFS
    // n * 2 ^ (n - 1)
    private static List<String> removeInvalidParentheses(String s) {
        List<String> answer = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return answer;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);
        boolean valid = false; // NOTE, valid here: we don't want to go further if we found min removal at a level, only check remaining item in that level
        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (isValid(current)) {
                answer.add(current);
                valid = true;
            }

            // this ensures once we've found a valid parentheses pattern, we don't do any further bfs using items pending in the queue since any further bfs
            // would only yield strings of smaller length. However the items already in queue need to be processed since there could be other solutions of the same length.
            // because we only need to remove minimum, no further than that
            // each level removes one character, so n level removes n characters
            if (valid) continue; // no need to process further since it is already valid

            // generate all possible states
            for (int i = 0; i < current.length(); i++) {
                // only proceed if it is ( or )
                if (current.charAt(i) != '(' && current.charAt(i) != ')') {
                    continue;
                }

                String next = current.substring(0, i) + current.substring(i + 1);

                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }

            }
        }

        return answer;
    }

    private static boolean isValid(String s) {
        // we only have ( and ) so no need for stack
        int count = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (count == 0) {
                    return  false;
                }
                count--;
            }
        }
        return count == 0;
    }
}
