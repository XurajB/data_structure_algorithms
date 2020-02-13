package dataStructures.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.Bfs(2);
    }

    static class Graph {
        int V;
        LinkedList<Integer>[] adjacentList;

        Graph(int V) {
            this.V = V;
            adjacentList = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjacentList[i] = new LinkedList<>();
            }
        }

        void addEdge(int from, int to) {
            adjacentList[from].add(to);
        }

        void Bfs(int entry) {

            // graphs contain cycles, so need to keep track of it
            boolean[] visited = new boolean[V];

            Queue<Integer> q = new LinkedList<>();
            q.add(entry);
            visited[entry] = true;

            while (!q.isEmpty()) {

                int i = q.poll();
                System.out.print(i + " ");

                LinkedList<Integer> list = adjacentList[i];

                for (Integer j : list) {
                    if (!visited[j]) {
                        q.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
    }
}
