package problems.graph.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 * For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it.
 * The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe.
 * Connections are bidirectional.
 * Find the minimum total cost to supply water to all houses.
 */
public class OptimizeWaterDistribution {
    public static void main(String[] args) {
        int[] wells = {1,2,2};
        int[][] pipes = {{1,2,1},{2,3,1}};
        System.out.println(minCostToSupplyWater(3, wells, pipes));
    }

    // O(ELogE), O(E)
    private static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[] parents = new int[n+1];

        // the idea is to use house 0 to lay pipes from 0 to i, with the cost of wells[i] - to represent cost of building wells
        // laying pipes from other houses or from house 0 which is equivalent to building well - we need to find minimum cost

        // all nodes are their own parents
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        // build edges
        List<int[]> edges = new ArrayList<>();
        for (int[] pipe: pipes) {
            edges.add(pipe);
        }
        for (int i = 0; i < n; i++) {
            edges.add(new int[] {0, i+1, wells[i]}); // simulate building well as laying pipe from house 0 to house i
        }

        // to minimize the cost
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        int ans = 0; // min cost
        for (int[] edge: edges) {
            int px = find(parents, edge[0]);
            int py = find(parents, edge[1]);

            if (px != py) {
                parents[px] = py;
                ans += edge[2];
            }
        }

        return ans;
    }

    private static int find(int[] parents, int x) {
        if (parents[x] != x) {
            parents[x] = find(parents, parents[x]); // path compression
        }
        return parents[x];
    }
}
