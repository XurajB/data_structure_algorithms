package problems.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 * https://leetcode.com/problems/minimum-knight-moves/
 */
public class MinimumKnightMove {
    public static class Node {
        private int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getId() {
            return x * 1000 + y;
        }
    }

    Queue<Node> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();

    // Time: O(n), n = max no of moves, Space: O(n)
    public int minKnightMoves(int x, int y) {
        int level = 0;
        q.offer(new Node(0, 0));
        while (!q.isEmpty()) {
            int currentSize = q.size();
            for (int i = 0; i < currentSize; i++) {
                Node node = q.poll();
                if (node.x == x && node.y == y) {
                    return level;
                }
                addNode(node.x + 1, node.y + 2, q);
                addNode(node.x + 2, node.y + 1, q);

                addNode(node.x + 2, node.y - 1, q);
                addNode(node.x + 1, node.y - 2, q);

                addNode(node.x - 1, node.y - 2, q);
                addNode(node.x - 2, node.y - 1, q);

                addNode(node.x - 1, node.y + 2, q);
                addNode(node.x - 2, node.y + 1, q);
            }
            level++;
        }
        return -1;
    }

    private void addNode(int x, int y, Queue<Node> q) {
        if((Math.abs(x) + Math.abs(y)) > 300) {
            return;
        }
        Node n = new Node(x, y);
        if (!visited.contains(n.getId())) {
            q.offer(n);
            visited.add(n.getId());
        }
    }
}
