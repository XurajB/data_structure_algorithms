package problems.math;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointToOrigin {
    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int[][] answer = kClosest(points, 2);
        for (int[] a: answer) {
            System.out.println(Arrays.toString(a));
        }
    }

    // nLogK, n
    private static int[][] kClosest(int[][] points, int K) {
        // max heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (getDistance(b) > getDistance(a)) {
                return 1;
            } else {
                return -1;
            }
        });

        for (int[] point: points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        int[][] answer = new int[K][2];
        int n = K-1;
        while (!pq.isEmpty()) {
            answer[n] = pq.poll();
            n--;
        }

        return answer;
    }

    private static double getDistance(int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);
    }
}
