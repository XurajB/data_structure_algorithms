package problems.backtracking;

import java.util.*;

/**
 * Given a set of words (without duplicates), find all word squares you can build from them.
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 */
public class WordSquares {
    public static void main(String[] args) {
        String[] words = {"area","lead","wall","lady","ball"};
        System.out.println(wordSquares(words));
    }

    // See #ValidWordSquare first
    // This is using diagonally symmetric property of valid squares
    private static List<List<String>> wordSquares(String[] words) {
        Map<String, Set<String>> prefix = new HashMap<>();
        for (String word: words) {
            for (int i = 1; i <= word.length(); i++) { // substring
                String str = word.substring(0, i);
                prefix.putIfAbsent(str, new HashSet<>());
                prefix.get(str).add(word);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> candidates;
        for (String word: words) {
            candidates = new ArrayList<>();
            candidates.add(word);
            backtrack(candidates, 1, words[0].length(), prefix, ans); // start from 1, because we already have 1st candidate
        }

        return ans;
    }

    private static void backtrack(List<String> candidates, int index, int length, Map<String, Set<String>> prefix, List<List<String>> ans) {
        if (index == length) {
            ans.add(new ArrayList<>(candidates));
            return;
        }

        // get next prefix
        StringBuilder sb = new StringBuilder();
        for (String candidate: candidates) {
            sb.append(candidate.charAt(index));
        }

        if (!prefix.containsKey(sb.toString())) {
            return;
        }

        for (String next: prefix.get(sb.toString())) {
            candidates.add(next);
            backtrack(candidates, index + 1, length, prefix, ans);
            candidates.remove(candidates.size() - 1);
        }
    }
}
