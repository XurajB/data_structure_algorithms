package problems.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
 * If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard).
 * It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 */
public class Leaderboard {
    Map<Integer, Integer> map;

    public Leaderboard() {
        map = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        map.put(playerId, map.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i: map.keySet()) {
            pq.offer(map.get(i));
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int total = 0;
        while (!pq.isEmpty()) {
            total += pq.poll();
        }
        return total;
    }

    public void reset(int playerId) {
        map.remove(playerId);
    }
}
