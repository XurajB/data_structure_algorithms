package problems.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombination {
    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }

    private static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> answer = new ArrayList<>();
        letterCombinationsRecursive(answer, digits, "", 0, map);
        return answer;
    }

    // Complexity: time - O(3^N X 4^M) where N = number of character in digits that maps to 3 digit strings (2,3,4,5,6,8), M = number of characters in
    // digits that maps to 4 digit strings (7,9)
    private static void letterCombinationsRecursive(List<String> answer, String digits, String current, int index, String[] map) {
        if (index == digits.length()) {
            answer.add(current);
            return;
        }
        String letters = map[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            letterCombinationsRecursive(answer, digits, current + letters.charAt(i), index + 1, map);
        }
    }

    // iterative
    private static List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits == null || digits.isEmpty()) {
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String remove = ans.poll();
                for (char s: mapping[x].toCharArray()) {
                    ans.add(remove + s);
                }
            }
        }
        return ans;
    }
}
