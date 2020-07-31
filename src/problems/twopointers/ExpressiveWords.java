package problems.twopointers;

/**
 * For some word, write the head character of every group, and the count of that group. For example, for "abbcccddddaaaaa", we'll write the "key" of "abcda", and the "count" [1,2,3,4,5].
 * Let's see if a word is stretchy. Evidently, it needs to have the same key as S.
 * Now, let's say we have individual counts c1 = S.count[i] and c2 = word.count[i].
 *  - If c1 < c2, then we can't make the ith group of word equal to the ith word of S by adding characters.
 *  - If c1 >= 3, then we can add letters to the ith group of word to match the ith group of S, as the latter is extended.
 *  - Else, if c1 < 3, then we must have c2 == c1 for the ith groups of word and S to match.
 */
public class ExpressiveWords {
    public static void main(String[] args) {
        String[] words = {"hello", "hi","helo"};
        System.out.println(expressiveWords("heeellooo", words));
    }

    // O(n * N), n = no of words, N size of max word
    // O(1)
    private static int expressiveWords(String S, String[] words) {
        if (S == null || words == null) {
            return 0;
        }
        int count = 0;
        for (String word: words) {
            if (isStretchy(S, word)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isStretchy(String S, String word) {
        if (word == null) {
            return false;
        }
        // two pointers
        int i = 0;
        int j = 0;
        while (i < S.length() && j < word.length()) {
            if (S.charAt(i) != word.charAt(j)) {
                // if current characters don't match, then word is not stretchy
                return false;
            }
            int len1 = getRepeatedLength(S, i); // stretched word
            int len2 = getRepeatedLength(word, j);
            // two cases
            // if len1 is < 3, that means it has not been stretched so it should equal to len2
            // if len1 is >= 3, than means it has been stretched, the len1 should be higher than len2 - otherwise it has not been stretched enough (or there are not enough letters in S to match word)
            if ((len1 < 3 && len1 != len2) || (len1 >= 3 && len1 < len2)) {
                return false;
            }
            i += len1;
            j += len2;
        }
        // make sure they both reached end, one of them might have ended early
        return i == S.length() && j == word.length();
    }

    private static int getRepeatedLength(String word, int start) {
        int end = start;
        while (end < word.length() && word.charAt(end) == word.charAt(start)) {
            end++;
        }
        return end - start;
    }
}
