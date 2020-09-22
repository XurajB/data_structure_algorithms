package problems.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner,
 * and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.
 * Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.
 */
public class RectangleArea2 {
    public static void main(String[] args) {

    }

    static int OPEN = -1;
    static int CLOSE = 1;
    public int rectangleArea(int[][] rectangles) {
        // mark the rectangle as enter and exit with -1, 1
        // so we know which rectangle are active when we line sweep
        List<int[]> events = new ArrayList<>(); // y, open/close, start, end
        for (int[] rect: rectangles) {
            events.add(new int[] {rect[1], OPEN, rect[0], rect[2]});
            events.add(new int[] {rect[3], CLOSE, rect[0], rect[2]});
        }

        // sort this based on y (height), allows us to linesweep from y = bottom of lowerst rectangle
        events.sort((a, b) -> a[0] - b[0]);

        // use treemap to store interval (start, end), count. sorted by start then by end
        TreeMap<Interval, Integer> active = new TreeMap<>((a, b) -> {
            if (a.start == b.start) {
                return a.end - b.end;
            }
            return a.start - b.start;
        });

        // first y at the bottom
        int curY = events.get(0)[0];
        long ans = 0;

        for (int[] event: events) {
            int y = event[0];
            int x1 = event[2];
            int x2 = event[3];

            // calculate sum of intervals in active set which is active intervals in prev line
            if (y > curY) {
                ans += calcInterval(active) * (y - curY);
                curY = y;
            }

            Interval interval = new Interval(x1, x2);
            if (event[1] == OPEN) {
                active.put(interval, active.getOrDefault(interval, 0) + 1);
            } else {
                active.put(interval, active.getOrDefault(interval, 0) - 1);
                if (active.get(interval) == 0) {
                    active.remove(interval);
                }
            }
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }

    private long calcInterval(TreeMap<Interval, Integer> map) {
        long q = 0;
        int cur = -1;
        for (Interval interval: map.keySet()) {
            cur = Math.max(cur, interval.start);
            q += Math.max(interval.end - cur, 0);
            cur = Math.max(cur, interval.end);
        }
        return q;
    }

    static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
