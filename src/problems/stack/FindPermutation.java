package problems.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * By now, you are given a secret signature consisting of character 'D' and 'I'.
 * 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers.
 * And our secret signature was constructed by a special integer array, which contains uniquely all the different number
 * from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by
 * array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4],
 * which are both illegal constructing special string that can't represent the "DI" secret signature.
 *
 * On the other hand, now your job is to find the lexicographically smallest permutation
 * of [1, 2, ... n] could refer to the given secret signature in the input.
 */
public class FindPermutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findPermutation("DIID")));
    }

    private static int[] findPermutation(String s) {
        int[] ans = new int[s.length() + 1];
        Deque<Integer> stack = new ArrayDeque<>();

        int j = 0;
        for (int i = 1; i <= s.length(); i++) {
            // since we are minimizing lexicographically, if it is increasing we can immediately use this number
            if (s.charAt(i-1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty()) {
                    ans[j++] = stack.pop();
                }
            } else {
                stack.push(i);
            }
        }

        stack.push(s.length() + 1);
        while (!stack.isEmpty()) {
            ans[j++] = stack.pop();
        }

        return ans;
    }
}
