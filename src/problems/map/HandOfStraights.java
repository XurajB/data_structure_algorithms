package problems.map;

import java.util.*;

/**
 * Alice has a hand of cards, given as an array of integers.
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * Return true if and only if she can.
 */
public class HandOfStraights {
    public static void main(String[] args) {
        int[] hand = {1,2,3,3,4,4,5,6};
        System.out.println(isNStraightHand(hand, 4));
    }

    // sorting
    // n * w
    public boolean isNStraightHand3(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return false;
        }
        int n = hand.length;
        if (n % W != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> count = new HashMap<>();
        for (int num: hand) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (int num: hand) {
            if (count.get(num) == 0) {
                continue;
            }
            for (int i = 0; i < W; i++) {
                if (count.getOrDefault(num+i, 0) > 0) {
                    count.put(num+i, count.get(num+i) - 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // O(NLogN * W)
    private static boolean isNStraightHand(int[] hand, int W) {
        int len = hand.length;
        if (len % W != 0) {
            return false;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num: hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (!map.isEmpty()) {
            int first = map.firstKey();
            for (int j = 1; j < W; j++) {
                int next = first + j;
                if (map.containsKey(next)) {
                    map.put(next, map.get(next) - 1);
                    if (map.get(next) == 0) {
                        map.remove(next);
                    }
                } else {
                    return false;
                }
            }
            // also update first
            map.put(first, map.get(first) - 1);
            if (map.get(first) == 0) {
                map.remove(first);
            }
        }
        return true;
    }

    /////////////////// - using heap
    // O(N^2 * W)
    public boolean isNStraightHand2(int[] hand, int W) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap, contains duplicates
        for (int i: hand) {
            pq.offer(i);
        }
        while (!pq.isEmpty()) {
            int start = pq.poll();
            // check if we have next consecutive hands
            for (int i = 1; i < W; i++) {
                int next = start + i;
                if (!pq.contains(next)) {
                    return false;
                }
                pq.remove(next); // this can take O(N)
            }
        }
        return true;
    }
}
