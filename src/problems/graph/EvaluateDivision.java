package problems.graph;

import java.util.*;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 */
public class EvaluateDivision {
    public static void main(String[] args) {
        String[][] equations = {{"a","b"}, {"b", "c"}};
        double[] values = {2.0,3.0};
        String[][] queries = {{"a","c"}, {"b","a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }

    // solving using graph, another way to solve is using union find
    // binary representation is represented as graph
    // going from a -> b we have a weight of k, but going from b -> a weight is going to be 1/k
    // so this is a directed weighted graph
    // to evaluate: a/b=2, b/c=3, a/c=? we find path from a to c and multiply weights along the path: 2*3, a/b * b/c = a/c (cancel out two Bs)
    // finding path can be done using DFS.
    // O(q * (e+v+e)) (extra edge for building graph)
    // O(e+v)
    private static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // we can represent our data following way
        // a -> b, k
        // b -> a, 1/k

        // build graph
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] ans = new double[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = getPathWeight(graph, queries[i][0], queries[i][1], new HashSet<>());
        }

        return ans;
    }

    // dfs
    private static double getPathWeight(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
        // rejection
        if (!graph.containsKey(start)) {
            return -1.0;
        }
        // going from a->a : a->b, b->a == 1.0
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        visited.add(start);
        for (Map.Entry<String, Double> neighbour: graph.get(start).entrySet()) {
            if (!visited.contains(neighbour.getKey())) {
                double weight = getPathWeight(graph, neighbour.getKey(), end, visited);
                if (weight != -1) {
                    return neighbour.getValue() * weight;
                }
            }
        }

        return -1.0;
    }

    private static Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;
        for (int i = 0; i < equations.length; i++) {
            u = equations[i][0];
            v = equations[i][1];

            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());

            graph.get(u).put(v, values[i]);
            graph.get(v).put(u, 1 / values[i]);
        }
        return graph;
    }
}
