package problems.heap;

import java.util.PriorityQueue;

/**
 * Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive characters are same. There can be at max A 'a', B 'b' and C 'c'.
 * https://leetcode.com/discuss/interview-question/330356
 * AKA Longest happy string
 * https://leetcode.com/problems/longest-happy-string/
 */
public class LongestStringWithout3ConsecutiveChars {
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1, 1, 8));
    }

    static class Pair {
        char c;
        int count;
        public Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a1, b1) -> (b1.count - a1.count));

        if (a > 0) {
            pq.offer(new Pair('a', a));
        }
        if (b > 0) {
            pq.offer(new Pair('b', b));
        }
        if (c > 0) {
            pq.offer(new Pair('c', c));
        }

        StringBuilder sb = new StringBuilder();
        // we are polling 2 pairs at once
        while (pq.size() > 1) {
            Pair pair1 = pq.poll();
            if (pair1.count >= 2) {
                // max 2 at a time
                sb.append(pair1.c).append(pair1.c);
                pair1.count -= 2;
            } else {
                sb.append(pair1.c);
                pair1.count -= 1;
            }

            Pair pair2 = pq.poll();
            if (pair2.count >= 2 && pair1.count < pair2.count) {
                // max 2 at a time
                sb.append(pair2.c).append(pair2.c);
                pair2.count -= 2;
            } else {
                sb.append(pair2.c);
                pair2.count -= 1;
            }

            if (pair1.count > 0) {
                pq.offer(pair1);
            }
            if (pair2.count > 0) {
                pq.offer(pair2);
            }
        }

        // case where pq has 1 item left
        if (!pq.isEmpty()) {
            if (sb.charAt(sb.length() - 1) != pq.peek().c) {
                Pair last = pq.poll();
                if (last.count >= 2) {
                    sb.append(last.c).append(last.c);
                } else {
                    sb.append(last.c);
                }
            }
        }
        return sb.toString();
    }
}
