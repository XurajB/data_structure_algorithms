package problems.string;

import java.util.Arrays;

/**
 * Given an input string , reverse the string word by word.
 * In-place
 */
public class ReverseWordsInAString2 {
    public static void main(String[] args) {
        char[] s = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWords(s);
        System.out.println(Arrays.toString(s));
    }

    private static void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int last = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, last, i - 1);
                last = i + 1;
            }

        }
        reverse(s, last, s.length - 1);
    }

    private static void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
