package problems.math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 */
public class MinimumAreaRectangle {
    public static void main(String[] args) {
        int[][] points = {{1,1},{1,3},{3,1},{3,3},{2,2}};
        System.out.println(minAreaRect(points));
    }

    // O(N^2)
    private static int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point: points) {
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]); // x, y
        }

        int min = Integer.MAX_VALUE;
        for (int[] p1: points) {
            for (int[] p2: points) {
                if (p1[0] == p2[0] || p2[1] == p1[1]) {
                    continue; // same line or same point
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
