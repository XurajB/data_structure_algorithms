package dataStructures.graph;

import java.util.Arrays;

/**
 * Created by Xuraj on 12/27/2019.
 *
 * Kruskal's Minimum Spanning Tree - Given a connected and undirected graph, a spanning tree of that graph
 * is a sub-graph that is a tree and connects all the vertices together. A single graph can have many different spanning trees.
 * A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected
 * graph is a spanning tree with weight less than or equal to the weight of every other spanning tree.
 * The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
 *
 * A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.
 * Steps -
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 *
 * Step 2 is performed using union-find algorithm
 */
public class KruskalMST {
    private int v, e; // number of vertices and edges
    private Edge[] edges; // numbers of edges

    KruskalMST(int v, int e) {
        this.v = v;
        this.e = e;
        edges = new Edge[e];
    }

    static class Edge implements Comparable<Edge> {
        int source, destination, weight;
        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    // class to represent a subset for union-find
    static class Subset {
        int parent, rank;
    }

    // function to find set of an element i
    private int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // union of two sets of x and y. by rank
    private void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].rank = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].rank = xroot;
        } else {
            // they are same
            subsets[yroot].rank = xroot;
            subsets[xroot].rank++;
        }
    }

    private void kruskalMst() {
        Edge[] results = new Edge[v];
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges

        // step 1 - sort edges based on their weights
        Arrays.sort(edges);

        // subsets
        Subset[] subsets = new Subset[v];
        for(int k=0; k<v; ++k)
            subsets[k]=new Subset();

        // initialize subsets. they are parents of their own
        for (int j = 0; j < v; ++j) {
            Subset subset = subsets[j];
            subset.rank = 0;
            subset.parent = j;
        }

        // number of edges in a minimum spanning tree is V-1
        while (e < v - 1) {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next = edges[i++];

            int x = find(subsets, next.source);
            int y = find(subsets, next.destination);

            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[e++] = next;
                union(subsets, x, y);
            }

            // else discard next
        }

        for (i = 0; i < e; ++i) {
            System.out.println(results[i].source + " -- " +
                    results[i].destination + " == " + results[i].weight);
        }
    }

    public static void main (String[] args) {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph

        KruskalMST graph = new KruskalMST(V, E);

        Edge edge1 = new Edge(0, 1, 10);
        Edge edge2 = new Edge(0, 2, 6);
        Edge edge3 = new Edge(0 ,3, 5);
        Edge edge4 = new Edge(1, 3, 15);
        Edge edge5 = new Edge(2, 3, 4);

        graph.edges[0] = edge1;
        graph.edges[1] = edge2;
        graph.edges[2] = edge3;
        graph.edges[3] = edge4;
        graph.edges[4] = edge5;

        graph.kruskalMst();
    }
}
