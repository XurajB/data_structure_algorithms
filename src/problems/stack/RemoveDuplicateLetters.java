package problems.stack;

import java.util.*;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
    }

    // O(N), we are only storing max 26 chars so O(1) on space
    private static String removeDuplicateLetters(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                while (!stack.isEmpty() && stack.peek() > s.charAt(i) && last[stack.peek() - 'a'] > i) {
                    set.remove(stack.pop());
                }
                stack.push(s.charAt(i));
                set.add(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
