package dataStructures.graph;

import java.util.*;

/**
 * Implementation of Dijkstra's shortest path algorithm from a start node to a
 * specific ending node. Dijkstra can also be modified to find the shortest path between a starting
 * node and all other nodes in the graph.
 * Note - Does not work with negative cost edges as the algorithm runs forever because it finds better path every time
 */
public class DijkstrasShortestPath {

    // Small epsilon value to comparing double values.
    private static final double EPS = 1e-6;

    // number of nodes
    private int n;
    // distance array
    private double[] distance;
    // path array
    private int[] previous;
    // graph
    private List<List<Edge>> graph;

    public DijkstrasShortestPath(int n) {
        this.n = n;
        createEmptyGraph(n);
    }

    public static void main(String[] args) {
        DijkstrasShortestPath dijkstrasShortestPath = new DijkstrasShortestPath(5);
        dijkstrasShortestPath.addEdge(0, 1, 2);
        dijkstrasShortestPath.addEdge(0, 4, 4);
        dijkstrasShortestPath.addEdge(1, 2, 3);
        dijkstrasShortestPath.addEdge(1, 3, 4);
        dijkstrasShortestPath.addEdge(1, 4, 8);
        dijkstrasShortestPath.addEdge(2, 3, 1);
        dijkstrasShortestPath.addEdge(3, 4, 2);

        System.out.println(dijkstrasShortestPath.getShortestPath(0, 4));
        System.out.println(dijkstrasShortestPath.getShortestPath(2, 4));
    }

    // class to represent directed edge between two nodes with specific cost
    public static class Edge {
        double cost;
        int from;
        int to;

        Edge(int from, int to, double cost) {
            this.cost = cost;
            this.from = from;
            this.to = to;
        }
    }

    // track nodes to visit the graph
    public static class Node {
        int id;
        double value; // to track shortest distance, used by priority queue

        Node(int id, double value) {
            this.id = id;
            this.value = value;
        }
    }

    private Comparator<Node> comparator = (node1, node2) -> {
        if (Math.abs(node1.value - node2.value) < EPS) return 0;
        return (node1.value - node2.value) > 0 ? +1 : -1;
    };

    // adds directed edge to the graph
    private void addEdge(int from, int to, double cost) {
        graph.get(from).add(new Edge(from, to, cost));
    }

    // Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
    private List<Integer> getShortestPath(int start, int end) {
        if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");

        double dist = dijkstra(start, end);

        List<Integer> path = new ArrayList<>();
        if (dist == Double.POSITIVE_INFINITY) return path;

        for (Integer at = end; at != null; at = previous[at]) path.add(at);
        Collections.reverse(path);

        return path;
    }

    // Run Dijkstra's algorithm on a directed graph to find the shortest path
    // from a starting node to an ending node. If there is no path between the
    // starting node and the destination node the returned value is set to be
    // Double.POSITIVE_INFINITY.
    private double dijkstra(int start, int end) { // end is not necessary but this is to optimize the algorithm
        // array of minimum distance to each node
        distance = new double[n];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;

        // Keep a priority queue of the next most promising node to visit.
        PriorityQueue<Node> queue = new PriorityQueue<>(n*2, comparator); // you might have to visit more than n nodes (optional)
        queue.offer(new Node(start, 0));

        // arrays to keep track of visited arrays
        boolean[] visited = new boolean[n];
        previous = new int[n];

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.id] = true;

            // we already found a better path before processing this
            if (distance[node.id] < node.value) {
                continue;
            }

            for (Edge edge: graph.get(node.id)) {
                // you cannot get a shorter path by revisiting previously
                // visited node
                if (visited[edge.to]) {
                    continue;
                }

                double newDistance = distance[edge.from] + edge.cost;
                if (newDistance < distance[edge.to]) {
                    previous[edge.to] = edge.from;
                    distance[edge.to] = newDistance;
                    queue.offer(new Node(edge.to, newDistance));
                }
            }

            // Once we've visited all the nodes spanning from the end
            // node we know we can return the minimum distance value to
            // the end node because it cannot get any better after this point.
            if (node.id == end) {
                return distance[end];
            }
        }

        // end is unreachable
        return Double.POSITIVE_INFINITY;
    }

    private void createEmptyGraph(int n) {
        graph = new ArrayList<>(n);
        for (int i =0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
    }

}