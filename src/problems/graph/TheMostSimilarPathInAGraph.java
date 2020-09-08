package problems.graph;

import java.util.*;

/**
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi.
 * Each city has a name consisting of exactly 3 upper-case English letters given in the string array names.
 * Starting at any city x, you can reach any city y where y != x (i.e. the cities and the roads are forming an undirected connected graph).
 *
 * You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
 *
 * You need to return the order of the nodes in the path with the minimum edit distance,
 * The path should be of the same length of targetPath and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]).
 * If there are multiple answers return any one of them.
 */
public class TheMostSimilarPathInAGraph {
    public static void main(String[] args) {

    }

    // can also be solved with dijkstra's algo
    // DFS with memo
    // names * roads (r * j)
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[][] dp = new int[names.length][targetPath.length]; // edit distance from name to target, also visited
        int[][] nextChoiceForMin = new int[names.length][targetPath.length];
        for (int[] x: dp) {
            Arrays.fill(x, -1);
        }
        // build adjacency matrix
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] road: roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        // for each node, calculate min edit distance (or cost) and the city that gave the min cost
        int min = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0; i < names.length; i++) {
            int dist = dfs(i, 0, names, targetPath, dp, nextChoiceForMin, graph); // start from i, build path from 0
            if (dist < min) {
                start = i;
                min = dist;
            }
        }
        // build the ans based on best next choice
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < targetPath.length) {
            ans.add(start);
            start = nextChoiceForMin[start][ans.size() - 1];
        }
        return ans;
    }

    private int dfs(int nameIndex, int pathIndex, String[] names, String[] targetPath, int[][] dp, int[][] nextChoiceForMin, Map<Integer, List<Integer>> graph) {
        if (dp[nameIndex][pathIndex] != -1) {
            return dp[nameIndex][pathIndex];
        }
        // get edit distance if it is different from the path
        int editDist = (names[nameIndex].equals(targetPath[pathIndex])) ? 0 : 1;
        // if we filled up the path
        if (pathIndex == targetPath.length - 1) {
            return editDist;
        }
        // from each neighbour calculate min cost, and city that gave the min cost
        int min = Integer.MAX_VALUE;
        for (int neighbour: graph.get(nameIndex)) {
            int neighbourCost = dfs(neighbour, pathIndex + 1, names, targetPath, dp, nextChoiceForMin, graph);
            if (neighbourCost < min) {
                min = neighbourCost;
                nextChoiceForMin[nameIndex][pathIndex] = neighbour;
            }
        }
        editDist += min;
        dp[nameIndex][pathIndex] = editDist;
        return editDist;
    }
}
