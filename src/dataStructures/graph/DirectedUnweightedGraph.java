package dataStructures.graph;

import java.util.*;

/**
 * directional unweighted graph
 * example to find if there is a path between two nodes using BFS
 */
public class DirectedUnweightedGraph {

    // graph
    private Map<Node, List<Node>> map = new HashMap<>();

    public enum State {
        VISITED, UNVISITED, VISITING
    }

    public static void main(String[] args) {
        DirectedUnweightedGraph graph = new DirectedUnweightedGraph();

        // nodes
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // edges are added
        graph.addEdge(node0, node1);
        graph.addEdge(node0, node4);
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node4);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node4);

        System.out.println(graph.toString());

        System.out.println(graph.searchPath(node0, node4));
        System.out.println(graph.searchPath(node0, node3));
        System.out.println(graph.searchPath(node4, node3));
        System.out.println(graph.searchPath(node0, node3));
    }

    // method to find if there is a path from one node to another
    private boolean searchPath(Node from, Node to) {

        // mark all nodes as unvisited
        for (Node node: map.keySet()) {
            node.state = State.UNVISITED;
        }

        // using queue for BFS
        LinkedList<Node> q = new LinkedList<>();
        Node n;
        q.add(from);

        while (!q.isEmpty()) {
            n = q.removeFirst();

            if (n != null) {
                for (Node adjacent: map.get(n)) {
                    if (adjacent.state == State.UNVISITED) {
                        if (adjacent == to) { // see if this is the destination
                            return true;
                        } else {
                            adjacent.state = State.VISITING;
                            q.add(adjacent);
                        }
                    }
                }
                n.state = State.VISITED;
            }
        }
        return false;
    }

    public static class Node {
        // node id
        int node;
        State state;

        Node(int node) {
            this.node = node;
        }
    }

    private void addEdge(Node source, Node destination) {
        if (!map.containsKey(source)) {
            map.put(source, new ArrayList<>());
        }
        if (!map.containsKey(destination)) {
            map.put(destination, new ArrayList<>());
        }

        // one direction
        List<Node> node = map.get(source);
        node.add(destination);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Node node: map.keySet()) {
            sb.append(" ").append(node.node).append(": ");

            for (Node edge: map.get(node)) {
                sb.append(edge.node).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}