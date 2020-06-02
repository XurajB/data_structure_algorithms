package problems.graph.unionfind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * There's an undirected connected graph with n nodes labeled 1..n.
 * But some of the edges has been broken disconnecting the graph.
 * Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.
 * https://leetcode.com/discuss/interview-question/357310
 *
 * Kruskal's MST using Union find
 */
public class MinimumCostToRepairEdges {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {1,2}, {2,3}, {3,4}, {4,5}, {1,5}
        };
        int[][] edgesToRepair = {
                {1,2,12}, {3,4,30}, {1,5,8}
        };

        ///

        int n2 = 6;
        int[][] edges2 = {
                {1,2}, {2,3}, {4,5}, {3, 5}, {1,6}, {2, 4}
        };
        int[][] edgesToRepair2 = {
                {1,6,410}, {2,4,800}
        };

        ///

        int n3 = 6;
        int[][] edges3 = {
                {1,2}, {2,3}, {4,5}, {5, 6}, {1,5}, {2, 4}, {3, 4}
        };
        int[][] edgesToRepair3 = {
                {1,5,110}, {2,4,84}, {3,4,79}
        };

        MinimumCostToRepairEdges cost = new MinimumCostToRepairEdges();
        System.out.println(cost.minimumCostToRepair(n3, edges3, edgesToRepair3));
    }

    private int n;
    private int[] parent;

    // ELogV
    private int minimumCostToRepair(int N, int[][] edges, int[][] edgesToRepair) {
        this.n = N;
        this.parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        int cost = 0;

        Set<String> set = new HashSet<>();
        for (int[] edge: edgesToRepair) {
            set.add(edge[0] + "#" + edge[1]);
        }

        // edges are already connected so do union
        // only do unions on non broken edges this time
        for (int[] edge: edges) {
            String path = edge[0] + "#" + edge[1];
            if (!set.contains(path)) {
                union(edge[0], edge[1]);
            }
        }

        // can also use priority queue instead. Same complexity
        Arrays.sort(edgesToRepair, (a, b) -> (a[2] - b[2]));

        for (int[] edgeToRepair: edgesToRepair) {
            int x = edgeToRepair[0];
            int y = edgeToRepair[1];

            if (find(x) != find(y)) {
                cost += edgeToRepair[2];
                union(x, y);
            }
        }

        return n == 1 ? cost : -1;
    }

    private int find(int v) {
        if (parent[v] == v) {
            return parent[v];
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    private void union(int v1, int v2) {
        if (find(v1) == find(v2)) return;
        int pv1 = find(v1);
        int pv2 = find(v2);
        if (pv1 != pv2) {
            parent[pv1] = pv2;
            n--;
        }
    }
}
