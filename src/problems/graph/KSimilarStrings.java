package problems.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 * https://leetcode.com/problems/k-similar-strings/
 */
public class KSimilarStrings {
    public static void main(String[] args) {
        System.out.println(KSimilarity("abbc", "bbac"));
    }

    private static int KSimilarity(String A, String B) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(A);
        visited.add(A);

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(B)) {
                    return result;
                }
                int j = 0;
                // skip already sorted characters
                while (j < current.length() && current.charAt(j) == B.charAt(j)) {
                    j++;
                }
                for (int k = j + 1; k < current.length(); k++) {
                    // check if next swap is target value for shortest path
                    if (current.charAt(k) == B.charAt(j) && current.charAt(k) != B.charAt(k)) {
                        String next = swap(current, j, k);
                        if (!visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            result++;
        }
        return result;
    }

    private static String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
