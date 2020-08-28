package problems.math;

import java.util.Random;
import java.util.TreeMap;

/**
 * Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.
 *
 * Note:
 *
 * An integer point is a point that has integer coordinates.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangles.
 * ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
 * length and width of each rectangle does not exceed 2000.
 * 1 <= rects.length <= 100
 * pick return a point as an array of integer coordinates [p_x, p_y]
 * pick is called at most 10000 times.
 */
public class RandomPointInNonOverlappingRects {
    public static void main(String[] args) {

    }

    TreeMap<Integer, Integer> treeMap; // prefix sum of area, rect index. we do this so that rect with low or high area have chances to be picked accordingly
    Random random;
    int areaSum;
    int[][] rects;

    // core strategy: randomly pick a rectangle, then randomly pick a point from that rectangle.
    // use tree map to get ceiling point from random area sum.
    RandomPointInNonOverlappingRects(int[][] rects) {
        treeMap = new TreeMap<>();
        this.rects = rects;
        areaSum = 0;
        random = new Random();
        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            areaSum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1); // +1 for edge
            treeMap.put(areaSum, i);
        }
    }

    public int[] pick() {
        // ceiling key is the least key greater than or equal to given key or null
        // nextInt() is non inclusive for the right bound, so we add 1
        int randomSum = treeMap.ceilingKey(random.nextInt(areaSum + 1));
        int[] rect = rects[treeMap.get(randomSum)];

        int x1 = rect[0];
        int x2 = rect[2];
        int y1 = rect[1];
        int y2 = rect[3];

        int x = x1 + random.nextInt(x2 - x1 + 1);
        int y = y1 + random.nextInt(y2 - y1 + 1);

        return new int[] {x, y};
    }
}
