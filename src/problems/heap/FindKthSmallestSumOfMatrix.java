package problems.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 *
 * You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.
 */
public class FindKthSmallestSumOfMatrix {
    public static void main(String[] args) {
        int[][] mat = {
                {1,3,11},
                {2,4,6}
        };
        System.out.println(kthSmallest(mat, 5));
    }

    // O(m * n * k * log(k))
    // O(k)
    private static int kthSmallest(int[][] mat, int k) {
        int col = Math.min(mat[0].length, k);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0); // start with 0 sum

        for (int[] row: mat) {
            // we start summing from first row, initially they will be first row elements. but we will only pick min out of those and add to next row, until end of row
            PriorityQueue<Integer> nextPq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i: pq) { // k times only
                for (int j = 0; j < col; j++) {
                    nextPq.offer(row[j] + i);

                    // just keep of size k
                    if (nextPq.size() > k) {
                        nextPq.poll();
                    }
                }
            }
            pq = nextPq;
        }

        return pq.poll();
    }
}
