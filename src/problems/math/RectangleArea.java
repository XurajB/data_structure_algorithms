package problems.math;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 */
public class RectangleArea {
    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    private static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int abcd = (C-A)*(D-B);
        int efgh = (G-E)*(H-F);

        int xoverlap = overlap(A, C, E, G);
        int yoverlap = overlap(B, D, F, H);

        int overlapArea = xoverlap * yoverlap;
        return abcd + efgh - overlapArea;
    }

    private static int overlap(int a, int c, int e, int g) {
        if (a > g || c < e) {
            return 0;
        }

        return Math.max(a,e) - Math.min(c, g);
    }
}
