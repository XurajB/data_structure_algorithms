package dataStructures.graph;

import java.util.Arrays;

/**
 * disjoint sets: no common elements
 * A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 * A union-find algorithm is an algorithm that performs two useful operations on such a data structure:
 *
 * Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
 * Union: Join two subsets into a single subset.
 *
 * Union-Find Algorithm can be used to check whether an undirected graph contains cycle or not.
 * This method assumes that the graph doesn’t contain any self-loops.
 * We can keep track of the subsets in a 1D array, called parent[].
 */
public class UnionFind { // graph
    private int v, e; // number of vertices and edges
    private Edge[] edges; // collection of edges

    UnionFind(int v, int e) {
        this.v = v;
        this.e = e;
        edges = new Edge[e];
    }

    static class Edge {
        int source, destination;
        Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    // find subset of element i
    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    // union of two subsets
    private void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    // find if the graph has cycle
    private boolean hasCycle(UnionFind graph) {
        int[] parent = new int[graph.v];

        // initialize with -1, that means each element are their own subsets
        Arrays.fill(parent, -1);

        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < graph.v; i++) {
            int x = graph.find(parent, graph.edges[i].source);
            int y = graph.find(parent, graph.edges[i].destination);
            if (x == y) {
                return true; // has cycle
            } else {
                union(parent, x, y);
            }
        }

        return false; // no cycle found
    }

    public static void main (String[] args) {
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2 */
        int V = 3, E = 3;
        UnionFind graph = new UnionFind(V, E);

        // add edge 0-1
        Edge edge1 = new Edge(0, 1);
        Edge edge2 = new Edge(1, 2);
        Edge edge3 = new Edge(0, 2);

        graph.edges[0] = edge1;
        graph.edges[1] = edge2;
        graph.edges[2] = edge3;

        if (graph.hasCycle(graph))
            System.out.println( "graph contains cycle" );
        else
            System.out.println( "graph doesn't contain cycle" );
    }

}
