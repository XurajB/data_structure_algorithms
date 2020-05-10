package problems.array;

/**
 * You are given an array of strings words and a string chars.
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 * Return the sum of lengths of all good strings in words.
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 */
public class WordsFormedByCharacters {
    public static void main(String[] args) {
        String[] words = {"cat","bt","hat","tree"};
        String chars = "atach";
        System.out.println(countCharacters(words, chars));
    }

    // O(N*n), N = number of words, n = avg length of word
    private static int countCharacters(String[] words, String chars) {
        int[] map = new int[26];
        for (char c: chars.toCharArray()) {
            map[c - 'a']++;
        }
        int total = 0;
        for (String word: words) {
            int[] wordArray = new int[26];
            for (char c: word.toCharArray()) {
                wordArray[c-'a']++;
            }
            boolean isValid = true;
            for (int i = 0; i < 26; i++) {
                if (wordArray[i] > map[i]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                total += word.length();
            }
        }
        return total;
    }
}
