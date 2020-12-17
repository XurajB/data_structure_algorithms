package problems.graph;

import java.util.*;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
 * you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinHeightTrees {
    public static void main(String[] args) {
        int[][] edges = {{3,0}, {3,1}, {3,2}, {3,4}, {5,4}};
        int[][] edges2 = {{1,0},{1,2},{1,3}};
        System.out.println(findMinHeightTrees(4, edges2));
    }

    private static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // naive way is to construct graph and do bfs for each node. TLE

        //topo
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }

        // there can be at most two MHT in a graph. middle, middle - 1 depending on odd/even n. center node forming mht.
        // first: remove all 1 degree nodes (leaves), do the same until there are at most 2 nodes
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }

        // start bfs from leaf, indegree = 1;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                queue.offer(i);
            }
        }

        while (n > 2) {
            int size = queue.size();
            n = n - size;

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int parent: graph.get(cur)) {
                    indegree[parent]--;
                    if (indegree[parent] == 1) {
                        queue.offer(parent);
                    }
                }
            }
        }

        ans.addAll(queue);
        return ans;
    }
}
