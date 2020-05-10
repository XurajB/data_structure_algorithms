package problems.graph;

import java.util.Arrays;

/**
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * To perform a "flood fill", consider the starting pixel,
 * plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 */
public class FloodFill {
    public static void main(String[] args) {
        int[][] image = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };

        int[][] answer = floodFill(image, 1, 1, 2);
        for (int[] ans: answer) {
            System.out.println(Arrays.toString(ans));
        }
    }

    // O(N), O(N)
    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) {
            return image;
        }
        int color = image[sr][sc];
        if (color != newColor) {
            // if newColor is the same as color, after visiting a neighbour point of a starting point, The DFS function would visit the starting point again and this loop never stops.
            // or keep a visited set
            dfs(image, sr, sc, newColor, color);
        }
        return image;
    }

    // dfs
    private static void dfs(int[][] image, int r, int c, int newColor, int oldColor) {
        if (r >= image.length || c >= image[0].length || r < 0 || c < 0 || image[r][c] != oldColor) {
            return;
        }

        image[r][c] = newColor;
        dfs(image, r+1, c, newColor, oldColor);
        dfs(image, r, c+1, newColor, oldColor);
        dfs(image, r-1, c, newColor, oldColor);
        dfs(image, r, c-1, newColor, oldColor);
    }
}
