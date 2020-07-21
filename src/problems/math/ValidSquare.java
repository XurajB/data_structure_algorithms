package problems.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 */
public class ValidSquare {
    public static void main(String[] args) {
        int[] p1 = {1,0};
        int[] p2 = {-1,0};
        int[] p3 = {0,1};
        int[] p4 = {0,-1};

        System.out.println(validSquare(p1, p2, p3, p4));
    }

    // all solutions below O(1)
    // we need to verify both all sides are same and diagonals are equal. Rhombus has all sides equal but not same length diagonals.
    // there are several ways to verify this
    // one way is to calculate all lengths of sides and save in a set, and make sure the set size is 2.. because all 4 sides are equal and 2 diagonals are equal
    private static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Double> distances = new HashSet<>();

        // this won't work if any of the two points are same, which means 3 sides
        if (isSame(p1, p2) || isSame(p1, p3) || isSame(p1, p4) || isSame(p2, p3) || isSame(p2, p4) || isSame(p3, p4)) {
            return false;
        }

        distances.add(getDistance(p1, p2));
        distances.add(getDistance(p1, p3));
        distances.add(getDistance(p1, p4));
        distances.add(getDistance(p2, p3));
        distances.add(getDistance(p2, p4));
        distances.add(getDistance(p3, p4));

        return distances.size() == 2;
    }

    private static double getDistance(int[] p1, int[] p2) {
        return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }

    private static boolean isSame(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }

    //---------------
    // get all distance and sort
    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
        double[] arr = {getDistance(p1, p2), getDistance(p1, p3), getDistance(p1, p4), getDistance(p2, p3), getDistance(p2, p4), getDistance(p3, p4)};
        Arrays.sort(arr);
        // check first four sides equal and last two equal
        return arr[0] > 0 && arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3] && arr[4] == arr[5];
    }

    //----------------
    // we can also sort the given points by x axis and if the are tie, then by y. This will give points in an arrangement of points in a valid square
    public static boolean validSquare3(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p={p1,p2,p3,p4};
        // if x is equal, sort by y
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
        return getDistance(p[0], p[1]) != 0
                && getDistance(p[0], p[1]) == getDistance(p[1], p[3])
                && getDistance(p[1], p[3]) == getDistance(p[3], p[2])
                && getDistance(p[3], p[2]) == getDistance(p[2], p[0])
                && getDistance(p[0], p[3]) == getDistance(p[1], p[2]);
    }
}
