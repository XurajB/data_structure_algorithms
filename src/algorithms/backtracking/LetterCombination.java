package algorithms.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<Integer, String> map = new HashMap<>();

        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> answer = new ArrayList<>();

        letterCombinationsRecursive(answer, digits, "", 0, map);

        return answer;
    }

    // Complexity: time - O(3^N X 4^M) where N = number of character in digits that maps to 3 digit strings (2,3,4,5,6,8), M = number of characters in
    // digits that maps to 4 digit strings (7,9)
    private static void letterCombinationsRecursive(List<String> answer, String digits, String current, int index, Map<Integer, String> map) {
        if (index == digits.length()) {
            answer.add(current);
            return;
        }

        String letters = map.get(digits.charAt(index) - '0');
        for (int i = 0; i < letters.length(); i++) {
            letterCombinationsRecursive(answer, digits, current + letters.charAt(i), index + 1, map);
        }
    }
}
