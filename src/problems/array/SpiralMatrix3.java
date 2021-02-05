package problems.array;

import java.util.Arrays;

/**
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
 * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
 * Now, we walk in a clockwise spiral shape to visit every position in this grid.
 * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
 * Eventually, we reach all R * C spaces of the grid.
 * Return a list of coordinates representing the positions of the grid in the order they were visited.
 */
public class SpiralMatrix3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(spiralMatrix3(3, 4, 0, 0)));
    }

    // steps between turns: 1 1 2 2 3 3 4 4 5 5..
    private static int[][] spiralMatrix3(int R, int C, int r0, int c0) {
        int[][] ans = new int[R*C][2];
        // dirs: right, down, left, up
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        int index = 0;
        ans[index++] = new int[] {r0, c0};
        int dir = 0;
        int len = 0; // steps in each direction
        while (index < R * C) {
            if (dir == 0 || dir == 2) {
                len++; // while moving east or west (right or left), increase step count. moving up or down will be left and right steps
            }

            // move len steps in cur direction
            for (int i = 0; i < len; i++) {
                r0 += dirs[dir][0];
                c0 += dirs[dir][1];

                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
                    ans[index++] = new int[] {r0, c0};
                }
            }
            // change dir
            dir = (dir + 1) % 4;
        }

        return ans;
    }
}
