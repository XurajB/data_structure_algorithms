package problems.graph;

import java.util.*;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
 * Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 */
public class BusRoutes {
    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        System.out.println(numBusesToDestination(routes, 1, 6));
    }

    // idea: we process bus stops in a level-wise manner, we add all bus stops reachable by all bus in that level.
    // this means all buses in one level are given equal distance
    private static int numBusesToDestination(int[][] routes, int S, int T) {
        if (routes == null || routes.length == 0) {
            return 0;
        }

        if (S == T) {
            return 0;
        }

        // map a stop to routes
        // 1 -> 0, stop 1 belongs to route 0
        // 7 -> 0, 1 stop 7 belongs to route 0 and 1
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop: routes[i]) {
                if (!graph.containsKey(stop)) {
                    graph.put(stop, new ArrayList<>());
                }
                graph.get(stop).add(i); // stop, routeid
            }
        }

        Queue<Integer> queue = new LinkedList<>(); // bus stop
        Set<Integer> visited = new HashSet<>(); // visited routes

        queue.offer(S);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int cur = queue.poll(); // current stop

                // get all routes available from the current stop
                for (int routeId: graph.get(cur)) {
                    if (!visited.contains(routeId)) {
                        visited.add(routeId);

                        // get all stops from this route
                        for (int stop: routes[routeId]) {
                            if (stop == T) {
                                return level;
                            }
                            queue.offer(stop);
                        }
                    }
                }
            }
            level++;
        }

        return -1;
    }
}