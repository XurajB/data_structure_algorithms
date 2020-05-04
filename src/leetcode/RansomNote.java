package leetcode;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines,
 * write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
 * Each letter in the magazine string can only be used once in your ransom note.
 * You may assume that both strings contain only lowercase letters.
 */
public class RansomNote {
    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }
    // O(N), O(1)
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) {
            return false;
        }
        int[] dict = new int[26];
        for (char c: magazine.toCharArray()) {
            dict[c - 'a']++;
        }
        for (char c: ransomNote.toCharArray()) {
            if (dict[c - 'a'] > 0) {
                dict[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }
}
