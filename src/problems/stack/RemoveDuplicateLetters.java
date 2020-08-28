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
        Map<Character, Integer> map = new HashMap<>(); // char, last occurrence
        Set<Character> seen = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && map.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                stack.push(c);
                seen.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c: stack) {
            sb.append(c.charValue());
        }
        return sb.reverse().toString();
    }
}
