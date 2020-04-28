package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = new int[] {1, 2, 5};
        System.out.println(coinChange(coins, 11));
    }

    // O(n * k), n = amount, k = num of coins in coins, O(n)
    private static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        queue.offer(amount);
        visited[amount] = true;
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            System.out.println(queue);
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                for (int coin: coins) {
                    int next = current - coin;
                    if (next == 0) {
                        return level;
                    } else if (next > 0 && !visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }

        return -1;

    }
}
