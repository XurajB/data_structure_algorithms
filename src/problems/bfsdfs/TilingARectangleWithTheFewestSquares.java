package problems.bfsdfs;

/**
 *
 */
public class TilingARectangleWithTheFewestSquares {

    public static void main(String[] args) {

    }

    /**
     * go through every point in the rectangle, starting from (0,0),(0,1)..(n,m)
     * if (rect[r..][c..] is available, then cover with k*k square starting at point (r,c)
     * try every possible size of square k*k where k = min(n-r, m-c)
     */
    private static int tilingRectangle(int n, int m) {
        dfs(0, 0, new boolean[n][m], 0);
        return ans;
    }

    static int ans = Integer.MAX_VALUE;

    // (r,c) is the starting point for selecting a square
    // rect records the status of current rectangle, count is the num of squares we have covered
    private static void dfs(int r, int c, boolean[][] rect, int count) {
        int n = rect.length;
        int m = rect[0].length;

        // optimization 1: if count >= ans
        if (count >= ans) {
            return; // this is not optimal
        }

        // we covered whole rectangle (row by row)
        if (r >= n) {
            ans = count;
            return;
        }

        // we covered the area [0,..n][0,..c] move to next row
        if (c >= m) {
            dfs(r+1, 0, rect, count);
            return;
        }

        // if (r,c) is already covered then move to next point
        if (rect[r][c]) {
            dfs(r, c+1, rect, count);
            return;
        }

        // try all possible size of squares starting from (r,c)
        // min(n-r, m-c) coz square
        for (int k = Math.min(n-r, m-c); k >= 1 && isAvailable(rect, r, c, k); k--) {
            // backtrack
            cover(rect, r, c, k);
            dfs(r, c+1, rect, count+1);
            uncover(rect, r, c, k);
        }
    }

    // check if area is available
    private static boolean isAvailable(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rect[r+i][c+j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // cover the area
    private static void cover(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                rect[r+i][c+j] = true;
            }
        }
    }

    // uncover the area
    private static void uncover(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                rect[r+i][c+j] = false;
            }
        }
    }
}
