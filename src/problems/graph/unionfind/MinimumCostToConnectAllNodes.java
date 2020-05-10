package problems.graph.unionfind;

import java.util.Arrays;

/**
 * Given an undirected graph with n nodes labeled 1..n. Some of the nodes are already connected.
 * The i-th edge connects nodes edges[i][0] and edges[i][1] together.
 * Your task is to augment this set of edges with additional edges to connect all the nodes.
 * Find the minimum cost to add new edges between the nodes such that all the nodes are accessible from each other.
 * https://leetcode.com/discuss/interview-question/356981
 *
 * Kruskal's MST
 * similar to ConnectingCitiesWithMinimumCost
 */
public class MinimumCostToConnectAllNodes {

    int[] parent;
    int n;

    public static void main(String[] args) {
        int[][] edges = {{1, 4}, {4, 5}, {2, 3}};
        int[][] newEdges = {{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
        MinimumCostToConnectAllNodes minCost = new MinimumCostToConnectAllNodes();
        System.out.println(minCost.findMinCost(6, edges, newEdges));
    }

    // O(ELogV)
    private int findMinCost(int N, int[][] edges, int[][] newEdges) {
        this.n = N;
        this.parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        int cost = 0;
        // edges are already connected so do union
        for (int[] edge: edges) {
            union(edge[0], edge[1]);
        }

        // can also use priority queue instead. Same complexity
        Arrays.sort(newEdges, (a, b) -> (a[2] - b[2]));

        for (int[] newEdge: newEdges) {
            int x = newEdge[0];
            int y = newEdge[1];

            if (find(x) != find(y)) {
                cost += newEdge[2];
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
