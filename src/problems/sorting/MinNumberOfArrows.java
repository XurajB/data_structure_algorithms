package problems.sorting;

import java.util.Arrays;

/**
 * There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice. The start is always smaller than the end.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.
 *
 * Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to burst all balloons.
 */
public class MinNumberOfArrows {
    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }

    private static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[0] < b[0] ? -1 : 1); // prevent overflow with -ve elements (-large, +large)
        int count = 1;
        int lastEnd = points[0][1];
        for (int[] point: points) {
            if (point[0] <= lastEnd) {
                lastEnd = Math.min(lastEnd, point[1]);
            } else {
                count++;
                lastEnd = point[1];
            }
        }

        return count;
    }
}
