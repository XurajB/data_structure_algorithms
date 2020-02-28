package leetcode;

import java.util.Arrays;
import java.util.Comparator;
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

    private static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(points.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                double dist1 = distance(a);
                double dist2 = distance(b);

                if (dist1 > dist2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
        }

        int[][] answer = new int[K][2];

        for (int i = 0; i < K; i++) {
            answer[i] = pq.poll();
        }

        return answer;
    }

    private static double distance(int[] a) {
        return Math.sqrt(a[0]*a[0] + a[1]*a[1]); // distance formula
    }
}
