package problems.array;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * Input: "aab"
 * Output: true
 * https://leetcode.com/problems/palindrome-permutation/
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("aaa"));
    }

    // O(n), O(1)
    // can use set as well
    private static boolean canPermutePalindrome(String s) {
        // palindrome is possible if all characters are present even number of times in even length word
        // or all except one character in odd length word.
        // considering characters fall in 128 ascii characters
        int[] map = new int[128]; // constant space
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            count += map[i] % 2;
        }
        return count <= 1;
    }
}
