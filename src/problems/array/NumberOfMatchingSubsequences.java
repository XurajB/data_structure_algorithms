package problems.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 */
public class NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(numMatchingSubseq("abcde", words));
    }

    // we group words starting with same letter
    // the move the remaining characters to different bucket
    // add to ans if the remaining string is length 1
    private static int numMatchingSubseq(String S, String[] words) {
        Map<Character, Queue<String>> map = new HashMap<>();
        int ans = 0;

        for (int i = 0; i < S.length(); i++) {
            if (!map.containsKey(S.charAt(i))) {
                map.put(S.charAt(i), new LinkedList<>());
            }
        }

        for (String word: words) {
            if (map.containsKey(word.charAt(0))) {
                map.get(word.charAt(0)).offer(word);
            }
        }

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            Queue<String> queue = map.get(c);

            int size = queue.size();
            // only loop through initial size, we may be updating queue if same chars
            for (int k = 0; k < size; k++) {
                String str = queue.poll();

                if (str.length() == 1) {
                    ans++;
                } else if (map.containsKey(str.charAt(1))) {
                    map.get(str.charAt(1)).add(str.substring(1));
                }
            }
        }

        return ans;
    }
}
