package problems.graph.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIsland2 {
    public static void main(String[] args) {
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        System.out.println(numIslands2(3, 3, positions));
    }

    // Union find. Take the 2d grid map as an undirected graph formatted as adjacency matrix and there is an edge between two horizontally or vertically adjacent nodes of value 1.
    // the the problem reduces to finding the number of connected components in the graph after each addLand operation
    // For each addLand operation at position (row, col), union it with its adjacent neighbors if they belongs to some islands, if none of its neighbors belong to any islands,
    // then initialize the new position as a new island (set parent value to itself) within Union Find.
    // O(K + NLogN), N = m*n to initialize union find, K = number of positions
    // O(m*n) space
    private static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
        List<Integer> ans = new ArrayList<>();
        // when we add position, we increment count.. when we do a union we decrement it
        int count = 0; // number of islands
        int[] parents = new int[m*n];
        Arrays.fill(parents, -1);
        for (int[] position: positions) {
            int parent = n * position[0] + position[1]; // transform 2d to 1d using linear mapping
            if (parents[parent] != -1) { // already part of another island
                ans.add(count);
                continue;
            }
            parents[parent] = parent; // add new island, self parent
            count++;
            // search all 4 directions to see if we can combine islands into one
            for (int[] dir: dirs) {
                int x = dir[0] + position[0];
                int y = dir[1] + position[1];

                int next = n * x + y;
                if (x < 0 || x >= m || y <0 || y >= n || parents[next] == -1) {
                    continue;
                }
                // find
                int parentNext = find(parents, next); // logn. for N find: NlogN, worst case N^2 since no balancing
                // union
                if (parent != parentNext) {
                    parents[parentNext] = parent;
                    count--;
                }
            }
            ans.add(count);
        }
        return ans;
    }

    // 4N operations = 4longN = NLogN
    private static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        }
        // find with path compression
        parents[x] = find(parents, parents[x]);
        return parents[x];
    }
}
