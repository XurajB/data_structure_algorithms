package leetcode;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class VerifyAlienDictionary {
    private static int[] seq = new int[26];

    public static void main(String[] args) {
        String sequence = "hlabcdefgijkmnopqrstuvwxyz";
        String[] words = {"hello", "leetcode"};

        System.out.println(isAlienSorted(words, sequence));
    }

    private static boolean isAlienSorted(String[] words, String sequence) {
        // we are converting the alien letters into our system
        // so we can compare them with our letters
        for (int i = 0; i < sequence.length() - 1; i++) {
            seq[sequence.charAt(i) - 'a'] = i;
        }

        for (int j = 0; j < words.length - 1; j++) {
            String word1 = words[j];
            String word2 = words[j+1];

            if (compare(word1, word2) > 0) {
                return false;
            }
        }

        return true;
    }

    private static int compare(String word1, String word2) {
        int l1 = 0, l2 = 0;

        int diff = 0;
        while (l1 <= word1.length() - 1 && l2 <= word2.length() - 1 && diff == 0) {
            diff = seq[word1.charAt(l1) - 'a'] - seq[word2.charAt(l2) - 'a'];

            l1++;
            l2++;
        }

        // hello, hell
        if (diff == 0) {
            return word1.length() - word2.length();
        } else {
            return diff;
        }
    }
}
