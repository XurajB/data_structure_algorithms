package problems.graph.unionfind;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are N cities numbered from 1 to N.
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
 * (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.
 * The cost is the sum of the connection costs used. If the task is impossible, return -1.
 * https://leetcode.com/problems/connecting-cities-with-minimum-cost/
 */

// Kruskal's MST
public class ConnectingCitiesWithMinimumCost {
    int[] parent;
    public static void main(String[] args) {
        ConnectingCitiesWithMinimumCost citiesWithMinimumCost = new ConnectingCitiesWithMinimumCost();

        int[][] connections = new int[][] {
                {1,2,5},
                {1,3,6},
                {2,3,1}
        };
        System.out.println(citiesWithMinimumCost.minimumCost(3, connections));
    }

    // ELogE
    private int minimumCost(int N, int[][] connections) {
        if (connections == null || connections.length == 0) {
            return -1;
        }
        parent = new int[N + 1]; // cases where nodes start with 1

        int cost = 0;

        // mark all nodes to be its own parents
        // also creating disjoint sets
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // sort connections based on cost (ascending order)
        // we use the lowest cost to make connections, if we have more than one - then we only use the min, since we won't do another union if they are already connected
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));

        // go through each connection and perform union/find
        for (int[] connection: connections) {
            int x = connection[0];
            int y = connection[1];

            if (find(x) != find(y)) {
                cost += connection[2];
                union(x, y);
                N--;
            }
        }

        return N == 1 ? cost : -1;
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        // if they have different parent
        // that means they belong to two sets
        // perform union
        if (px != py) {
            parent[px] = py;
        }
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        // compressing the path to improve complexity
        parent[x] = find(parent[x]);
        return parent[x];
    }
}
