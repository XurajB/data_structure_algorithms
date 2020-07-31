package problems.string;

import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
    }
    private static boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) {
            return false;
        }
        String s1 = removeBackspace(S);
        String s2 = removeBackspace(T);
        return s1.equals(s2);
    }
    // O(M+N), O(M+N)
    private static String removeBackspace(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack);
    }

    /////////
    // two pointer approach
    private static boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int count = 0;
        while (i >= 0 || j >= 0) {
            count = 0;
            while (i >= 0 && (count > 0 || S.charAt(i) == '#')) {
                if (S.charAt(i) == '#') count++;
                else count--;
                i--;
            }

            count = 0;
            while (j >= 0 && (count > 0 || T.charAt(j) == '#')) {
                if (T.charAt(j) == '#') count++;
                else count--;
                j--;
            }

            char left = i < 0 ? '#' : S.charAt(i);
            char right = j < 0 ? '#' : T.charAt(j);

            if (left != right) {
                return false;
            }

            i--;
            j--;
        }
        return true;
    }
}
