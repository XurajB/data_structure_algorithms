package problems.graph;

import java.util.*;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 */
public class CheapestFlightWithinKStops {
    public static void main(String[] args) {
        int[][] flights = {{0,1,100}, {1,2,100}, {0,2,500}};
        System.out.println(findCheapestPrice(3, flights, 0, 2, 1));
    }

    /*
    A standard rule of thumb that is followed for solving shortest path problems is that we mostly use Breadth-first search for unweighted graphs and use Dijkstra's algorithm for weighted graphs.
    An implied condition to apply the Dijkstra's algorithm is that the weights of the graph must be positive.
    If the graph has negative weights and can have negative weighted cycles, we would have to employ another algorithm called the Bellman Ford's.
    We solve it 2 ways - dijkstra's and dfs
     */

    // dijkstra's O(V^2 logV)
    private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>(); // src, [dst, cost]
        for (int[] flight: flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            graph.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[] {src, K+1, 0}); // node, remaining stops, cost
        while (!pq.isEmpty()) {
            int[] point = pq.poll();
            int cur = point[0];
            int stop = point[1];
            int cost = point[2];
            if (cur == dst) {
                return cost;
            }
            if (stop > 0) {
                if (graph.containsKey(cur)) {
                    for (int[] next : graph.get(cur)) {
                        pq.offer(new int[]{next[0], stop - 1, cost + next[1]}); // next is from graph, next[1] cost
                    }
                }
            }
        }
        return -1;
    }

    // dfs (V^2 * K)
    static int res;
    private static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        res = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> graph = new HashMap<>(); // src, [dst, cost]
        for (int[] flight: flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            graph.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }
        dfs(graph, src, dst, K+1, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static void dfs(Map<Integer, List<int[]>> graph, int src, int dst, int k, int cost) {
        if (k < 0) {
            return;
        }
        if (dst == src) {
            res = cost;
            return;
        }
        if (!graph.containsKey(src)) {
            return;
        }
        for (int[] i: graph.get(src)) {
            if (cost + i[1] > res) {
                // pruning, to check the sum of current price and max cost. If it is greater than the ans so far, continue
                continue;
            }
            dfs(graph, i[0], dst, k-1, cost+i[1]);
        }
    }
}
