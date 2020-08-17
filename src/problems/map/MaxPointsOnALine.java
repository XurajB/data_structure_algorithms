package problems.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class MaxPointsOnALine {
    public static void main(String[] args) {

    }
    private static int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }

        if (points.length <= 2) {
            return points.length;
        }

        int ans = 0;
        // line eq: y = mx + c, determined by two factors: m and c if two points are on same line
        // slope: m = y2-y1/x2-x1.
        // if a third point x3,y3 is in the same line then we must have: y3 = x3 * m + c
        // thus y3-y1/x3-x1 = y2-y1/x2-x1 = m

        for (int i = 0; i < points.length; i++) {
            //calculate how many other points are one same line containing each base point
            Map<String, Integer> map = new HashMap<>();
            int duplicate = 0;
            int max = 0;

            for (int j = i + 1; j < points.length; j++) {
                // calculate m
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];

                if (deltaX == deltaY) {
                    duplicate++;
                    continue;
                }

                // slope is stored as in irreducible fraction. to store the fraction, we need to
                // reduce num and denominator to smallest value possible
                int gcd = gcd(deltaX, deltaY);
                int dx = deltaX/gcd;
                int dy = deltaY/gcd;

                String key = dx + "," + dy;

                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }

            ans = Math.max(ans, max + duplicate + 1);
        }

        return ans;
    }

    private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x%y);
    }
}
