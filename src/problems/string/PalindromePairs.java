package problems.string;

import java.util.*;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 */
public class PalindromePairs {
    public static void main(String[] args) {
        PalindromePairs palindromePairs = new PalindromePairs();
        System.out.println(palindromePairs.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
    }

    // O(k^2 * n), n = no. of words, k = length of longest word
    // O((k+n)^2)
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (String word: map.keySet()) {
            int currentIndex = map.get(word);
            String reverse = new StringBuilder(word).reverse().toString();
            // check if reverse is present in map
            // example: abcd, dcba (same length and reverse of each other - because palindrome center is middle)
            if (map.containsKey(reverse) && map.get(reverse) != currentIndex) {
                ans.add(Arrays.asList(currentIndex, map.get(reverse)));
            }
            // case 2 - different length, valid suffix. Palindrome center is not middle.
            // example: banana, nab. ana is palindrome, so rest of banana which is 'ban' needs to map with 'nab' which is reverse
            for (String suffix: allValidSuffixes(word)) {
                String reverseSuffix = new StringBuilder(suffix).reverse().toString();
                if (map.containsKey(reverseSuffix)) {
                    ans.add(Arrays.asList(map.get(reverseSuffix), currentIndex));
                }
            }
            // case 3 - different length, valid prefix
            // example: sssll, lls. ss is palindrome, so the rest 'ssl' needs to map with 'lls' which is reverse
            for (String prefix : allValidPrefixes(word)) {
                String reversePrefix = new StringBuilder(prefix).reverse().toString();
                if (map.containsKey(reversePrefix)) {
                    ans.add(Arrays.asList(currentIndex, map.get(reversePrefix)));
                }
            }
        }
        return ans;
    }

    private List<String> allValidSuffixes(String word) {
        List<String> validSuffixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, 0, i)) {
                validSuffixes.add(word.substring(i+1, word.length()));
            }
        }

        return validSuffixes;
    }

    private List<String> allValidPrefixes(String word) {
        List<String> validPrefixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, i, word.length() - 1)) {
                validPrefixes.add(word.substring(0, i));
            }
        }
        return validPrefixes;
    }

    private boolean isPalindrome(String word, int front, int back) {
        while (front < back) {
            if (word.charAt(front) != word.charAt(back)) return false;
            front++;
            back--;
        }
        return true;
    }
}
