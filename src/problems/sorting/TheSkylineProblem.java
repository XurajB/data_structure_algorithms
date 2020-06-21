package problems.sorting;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo,
 * write a program to output the skyline formed by these buildings collectively.
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 */
public class TheSkylineProblem {
    public static void main(String[] args) {
        int[][] buildings = new int[][] {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(getSkyline(buildings));
    }

    // line sweep technique
    // O(nlogn)
    private static List<List<Integer>> getSkyline(int[][] buildings) {
        // generate list of all points (start, end) with height
        // we need to mark start or end (trick here is to use -height for left edge (start))
        List<int[]> heights = new ArrayList<>();
        for (int[] building: buildings) {
            heights.add(new int[] {building[0], -building[2]}); // left edge
            heights.add(new int[] {building[1], building[2]});
        }
        // sort the heights by left edge
        // also check if they have same start (because we have -ve heights)
        Collections.sort(heights, (a,b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        // we need to keep track of the max height, heap has O(n) remove. so we use TreeMap for (logn) removal
        List<List<Integer>> ans = new ArrayList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // height, number of this height
        treeMap.put(0, 1);
        // prev height is 0, to make sure we are not adding same points twice if two buildings have same height
        int prev = 0;

        // visit all points in order
        for (int[] height: heights) {
            if (height[1] < 0) {
                // start point or left edge
                // add to the pq. -height to make it positive
                treeMap.put(-height[1], treeMap.getOrDefault(-height[1], 0) + 1);
            } else {
                // end point
                // there can be buildings with same height with higher right edge
                if (treeMap.get(height[1]) > 1) {
                    treeMap.put(height[1], treeMap.get(height[1]) - 1);
                } else {
                    treeMap.remove(height[1]);
                }
            }
            int cur = treeMap.lastKey(); // this is like pq.peek()
            // compare current max height with prev max height, update result
            // and prev max height if necessary
            if (cur != prev) {
                ans.add(Arrays.asList(height[0], cur));
                prev = cur;
            }
        }

        return ans;
    }
}
