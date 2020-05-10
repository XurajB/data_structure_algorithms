package problems.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given with a string . Your task is to remove at most two substrings of any length from the given string such that the remaining string contains vowels('a','e','i','o','u') only.
 * Your aim is the maximise the length of the remaining string. Output the length of remaining string after removal of atmost two substrings.
 * NOTE: The answer may be 0, i.e. removing the entire string
 * https://leetcode.com/discuss/interview-question/233724
 */
public class LongestStringMadeOfVowels {
    public static void main(String[] args) {
        String s1 = "earthproblem";
        String s2 = "letsgosomewhere";
        System.out.println(longestString(s1));
        System.out.println(longestString(s2));
    }

    // O(N), O(1)
    private static int longestString(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        int len = s.length();
        int start = 0;
        int end = len - 1;

        // 3 cases
        // case 1 - vowels at start
        while (vowels.contains(s.charAt(start))) {
            start++;
        }
        // case 2 - vowels at end
        while (vowels.contains(s.charAt(end))) {
            end--;
        }

        // add vowels from start + end
        int countSoFar = start + (len - end - 1);
        int max = 0;
        int count = 0;

        // case 3 - vowels in middle (breaks into two substrings)
        for (int i = start + 1; i <= end; i++) {
            if (vowels.contains(s.charAt(i))) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }

        return max + countSoFar;
    }
}
