package problems.map;

import java.util.PriorityQueue;
import java.util.TreeMap;

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
