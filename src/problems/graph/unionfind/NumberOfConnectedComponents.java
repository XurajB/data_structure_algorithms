package problems.graph.unionfind;

import java.util.*;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 */
public class NumberOfConnectedComponents {
    // union find
    // v + e after path compression
    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge: edges) {
            int x = find(parents, edge[0]);
            int y = find(parents, edge[1]);
            // union
            if (x != y) {
                parents[x] = y;
                n--;
            }
        }
        return n;
    }

    private int find(int[] parents, int v) {
        if (parents[v] == v) {
            return parents[v];
        }
        parents[v] = find(parents, parents[v]);
        return parents[v];
    }

    /////////////////////////////////////////////////////
    // dfs
    // V * E
    public int countComponents2(int n, int[][] edges) {
        Set<Integer> visited = new HashSet<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                dfs(map, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(Map<Integer, List<Integer>> map, Set<Integer> visited, int n) {
        visited.add(n);

        for (int neigh: map.get(n)) {
            if (!visited.contains(neigh)) {
                dfs(map, visited, neigh);
            }
        }
    }
}
