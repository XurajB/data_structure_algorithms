package problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b.
 * Any server can reach any other server directly or indirectly through the network.
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * Return all critical connections in the network in any order.
 * https://leetcode.com/problems/critical-connections-in-a-network/
 * https://www.youtube.com/watch?v=aZXi1unBdJA
 *
 * Bridges and articulation points in a graph
 */
public class CriticalConnectionsInANetwork {

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));

        CriticalConnectionsInANetwork criticalConn = new CriticalConnectionsInANetwork();
        System.out.println(criticalConn.criticalConnections(4, connections));
    }

    int T = 1; // to keep track of the discovery time or order of the node
    int[] visitedTimes;
    int[] lowTimes;
    List<List<Integer>> criticalConns;
    List<Integer>[] graph;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList[n];
        criticalConns = new ArrayList<>();
        visitedTimes = new int[n];
        lowTimes = new int[n];

        // build graph
        buildGraph(n, connections);

        // dfs
        boolean[] visited = new boolean[n];
        dfs(visited, 0, -1);

        return criticalConns;
    }

    // O(E + V)
    private void dfs(boolean[] visited, int current, int parent) {
        visited[current] = true;
        visitedTimes[current] = lowTimes[current] = T++;

        for (int neighbour: graph[current]) {
            // we don't want to revisit parent because we want to find another way to get to current node
            if (neighbour == parent) continue;
            if (!visited[neighbour]) {
                dfs(visited, neighbour, current);
                lowTimes[current] = Math.min(lowTimes[current], lowTimes[neighbour]);
                if (lowTimes[neighbour] > visitedTimes[current]) {
                    criticalConns.add(Arrays.asList(current, neighbour));
                }
            } else {
                // we found a back edge
                // if this is not our parent and it has already been visited, means there is an another way to visit this
                lowTimes[current] = Math.min(lowTimes[current], visitedTimes[neighbour]);
            }
        }
    }

    private void buildGraph(int n, List<List<Integer>> connections) {
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection: connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }
    }
}
