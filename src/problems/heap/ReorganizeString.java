package problems.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.  If not possible, return the empty string.
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(reorganizeString("aaab"));
    }

    // O(NLogK), O(N) (k = size of alphabet - 26) so O(N)
    private static String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();

        PriorityQueue<CharacterCount> q = new PriorityQueue<>(new Comparator<CharacterCount>() {
            @Override
            public int compare(CharacterCount o1, CharacterCount o2) {
                return o2.count - o1.count;
            }
        });

        for (int i = 0; i < S.length(); i++) {
            int count = map.getOrDefault(S.charAt(i), 0);
            map.put(S.charAt(i), count + 1);
        }

        for (Character c: map.keySet()) {
            int count = map.get(c);
            q.add(new CharacterCount(c, count));
        }

        StringBuilder sb = new StringBuilder();

        while (q.size() > 1) {
            CharacterCount count = q.poll();
            CharacterCount count2 = q.poll();
            sb.append(count.c);
            sb.append(count2.c);
            if (count.count > 1) {
                count.count--;
                q.add(count);
            }
            if (count2.count > 1) {
                count2.count--;
                q.add(count2);
            }
        }

        if (q.size() > 0) {
            CharacterCount characterCount = q.poll();
            sb.append(characterCount.c);
        }

        if (sb.length() == S.length()) {
            return sb.toString();
        }
        return "";
    }

    static class CharacterCount {
        char c;
        int count;
        CharacterCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}
