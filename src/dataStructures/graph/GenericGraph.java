package dataStructures.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A generic graph implementation using LinkedLists.
 * Undirected and unweighted
 * Adjacency lists are represented by LinkedLists.
 */
public class GenericGraph<T> {

    // map to store the edges in the graph
    private Map<T, List<T>> map = new HashMap<>();

    public static void main(String[] args) {
        // creating the graph
        GenericGraph<Integer> graph = new GenericGraph<>();

        // edges are added
        graph.addEdge(0, 1, true);
        graph.addEdge(0, 4, true);
        graph.addEdge(1, 2, true);
        graph.addEdge(1, 3, true);
        graph.addEdge(1, 4, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(3, 4, true);

        System.out.println(graph.toString());
        System.out.println("Edge count: " + graph.getEdgeCount(true));
        System.out.println("Vertex count: " +graph.getVertexCount());
        System.out.println(graph.hasEdge(1, 9));
        System.out.println(graph.hasEdge(3, 4));
        System.out.println(graph.hasVertex(3));
        System.out.println(graph.hasVertex(10));
    }

    private void addVertex(T s) {
        map.put(s, new LinkedList<T>());
    }

    // add edge between source and destination
    private void addEdge(T source, T destination, boolean biDirectional) {
        // if graph does not contain source, add vertex
        if (!map.containsKey(source)) {
            addVertex(source);
        }

        // if graph does not contain destination, add vertex
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }

        // add new edge
        map.get(source).add(destination);
        if (biDirectional) {
            map.get(destination).add(source);
        }
    }

    private int getVertexCount() {
        return map.keySet().size();
    }

    private int getEdgeCount(boolean bidirectional) {
        int count = 0;
        for (T vertex: map.keySet()) {
            count = count + map.get(vertex).size();
        }
        if (bidirectional) {
            count = count / 2;
        }
        return count;
    }

    private boolean hasVertex(T s) {
        return map.containsKey(s);
    }

    private boolean hasEdge(T source, T destination) {
        List<T> vertex = map.get(source);
        if (vertex != null) {
            return vertex.contains(destination);
        }

        return false;
    }

    // print adjacency list
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T vertex: map.keySet()) {
            builder.append(" ").append(vertex.toString()).append(": ");
            for (T edges: map.get(vertex)) {
                builder.append(edges.toString()).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
