package problems.graph;

import java.util.*;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
public class CutOffTrees {
    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(1,2,3));
        forest.add(Arrays.asList(0,0,4));
        forest.add(Arrays.asList(7,6,5));

        System.out.println(cutOffTree(forest));
    }

    // O(r^2 * c^2)
    private static int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        int m = forest.size();
        int n = forest.get(0).size();

        // order by height
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]); // i, j, height

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) { // if it is a tree
                    pq.offer(new int[] {i, j, forest.get(i).get(j)});
                }
            }
        }

        // stat with 0, 0
        int[] start = new int[2];
        int sum = 0;
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int steps = minStep(forest, start, next, m, n);
            if (steps == -1) {
                return -1;
            }
            sum += steps;
            // set start to this tree now
            start[0] = next[0];
            start[1] = next[1];
        }
        return sum;
    }

    // min step to go from current tree to next min height tree
    private static int minStep(List<List<Integer>> forest, int[] start, int[] next, int m, int n) {
        int steps = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;

        int[][] dirs = new int[][] {
                {1,0},{0,1},{-1,0},{0,-1}
        };

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] current = q.poll();
                // check if we reached from start to next tree
                if (current[0] == next[0] && current[1] == next[1]) {
                    return steps;
                }

                for (int[] dir: dirs) {
                    int nr = current[0] + dir[0];
                    int nc = current[1] + dir[1];
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n || forest.get(nr).get(nc) == 0 || visited[nr][nc]) {
                        continue;
                    }
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
            steps++;
        }
        return -1;
    }
}
