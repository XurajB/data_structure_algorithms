package problems.math;

/**
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.
 * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.
 * Given two (axis-aligned) rectangles, return whether they overlap.
 * https://leetcode.com/problems/rectangle-overlap/
 */
public class RectangleOverlap {
    public static void main(String[] args) {
        int[] rec1 = new int[] {0,0,2,2};
        int[] rec2 = {1,1,3,3};
        System.out.println(isRectangleOverlap(rec1, rec2));
    }

    private static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // if there is an overlap - lower left coordinate of one is always less than upper right
        if (rec1[0] >= rec2[2] || rec2[0] >= rec1[2]) {
            return false;
        }
        if (rec1[1] >= rec2[3] || rec2[1] >= rec1[3]) {
            return false;
        }
        return true;
    }
}
