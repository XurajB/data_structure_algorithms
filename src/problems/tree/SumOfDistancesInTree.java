package problems.tree;

import java.util.*;

/**
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 * Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
 */
public class SumOfDistancesInTree {
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        System.out.println(Arrays.toString(sumOfDistancesInTree(6, edges)));
    }

    // O(N)
    static Map<Integer, List<Integer>> graph;
    static int[] sum;
    static int[] count;
    static int n;
    private static int[] sumOfDistancesInTree(int N, int[][] edges) {
        graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        n = N;
        sum = new int[N]; // sum of distance in subtree
        count = new int[N]; // count nodes in subtree

        // post order, update count and sum
        dfs(0, -1);
        // pre order, update sum
        dfs2(0, -1);

        return sum;
    }

    private static void dfs(int root, int prev) {
        for (int i: graph.get(root)) {
            if (i == prev) {
                continue;
            }
            dfs(i, root);
            count[root] += count[i];
            sum[root] += sum[i] + count[i];
        }
        count[root]++;
    }

    private static void dfs2(int root, int prev) {
        for (int i: graph.get(root)) {
            if (i == prev) {
                continue;
            }
            sum[i] = sum[root] - count[i] + n - count[i];
            dfs2(i, root);
        }
    }
}
