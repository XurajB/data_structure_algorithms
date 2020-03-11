package leetcode;

import java.util.ArrayList;

/**
 * Compute all permutations of a string of unique characters
 * O(n!) - n factorial (n * (n-1) * (n-2) *.. 1)
 */
public class StringPermutation {
    public static void main(String[] args) {
        ArrayList<String> results = findPerm("suraj");
        System.out.println(results);
        System.out.println(results.size());
    }

    private static ArrayList<String> findPerm(String s) {
        if (s == null) {
            return null;
        }

        ArrayList<String> permutation = new ArrayList<>();
        if (s.length() == 0) { //base case
            permutation.add("");
            return permutation;
        }

        char firstChar = s.charAt(0);
        String reminder = s.substring(1);

        ArrayList<String> words = findPerm(reminder);
        for (String word: words) {
            for (int i = 0; i <= word.length(); i++) {
                permutation.add(insertAt(word, firstChar, i));
            }
        }

        return permutation;
    }

    private static String insertAt(String word, char c, int position) {
        String firstPart = word.substring(0, position);
        String lastPart = word.substring(position);
        return firstPart + c + lastPart;
    }
}