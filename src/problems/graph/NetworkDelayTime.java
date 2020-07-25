package problems.graph;

import java.util.*;

/**
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it
 * takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(networkDelayTime(times, 4, 2));
    }

    // O(ELogE) time
    // O(V + E) where N is V
    private static int networkDelayTime(int[][] times, int N, int K) {
        // build graph, directed weighted
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] time: times) {
            int from = time[0];
            int to = time[1];
            int distance = time[2];
            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, distance);
        }

        // we want to measure shortest time to reach every node, which we can use heap to do this
        // making this a Dijkstra's algorithm
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // node, distance from K
        pq.offer(new int[] {K, 0});

        // keep a visited array
        boolean[] visited = new boolean[N+1];
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curDist = current[1];

            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            ans = curDist; // no need to keep calculating max, because pq will take care of it
            N--;
            if (graph.containsKey(curNode)) {
                for (int next: graph.get(curNode).keySet()) {
                    pq.offer(new int[] {next, curDist + graph.get(curNode).get(next)});
                }
            }
        }

        // check if we visited every node
        return (N == 0) ? ans : -1;
    }
}
