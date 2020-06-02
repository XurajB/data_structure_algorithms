package problems.heap;

import java.util.*;

/**
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */
public class RearrangeStringKDistanceApart {
    public static void main(String[] args) {
        String s = "aabbcc";
        System.out.println(rearrangeString(s, 3));
    }

    // O(NlogN), O(N) if only lowercase in string
    // O(N)
    private static String rearrangeString(String s, int k) {
        if (s.length() == 0) {
            return "";
        }

        // char,count map
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c: s.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

        // max heap
        Queue<CharCount> pq = new PriorityQueue<>((a,b) -> b.count - a.count);
        for (char c: charMap.keySet()) {
            pq.offer(new CharCount(c, charMap.get(c)));
        }

        // wait queue so we get same node after k
        Queue<CharCount> waitQueue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            CharCount current = pq.poll();
            sb.append(current.c);
            current.count--;
            waitQueue.offer(current);
            // only put it back in heap if wait queue is size k
            if (waitQueue.size() >= k) {
                CharCount wait = waitQueue.poll();
                if (wait.count > 0) { // only offer to heap if count is > 0
                    pq.offer(wait);
                }
            }
        }

        // check if full string is formed
        if (sb.length() == s.length()) {
            return sb.toString();
        }
        return "";
    }

    static class CharCount {
        char c;
        int count;
        CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}
