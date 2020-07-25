package problems.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 */
public class MaxDepthOfNaryTree {
    // O(N), O(logN)
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (Node node: root.children) {
            depth = Math.max(depth, maxDepth(node));
        }
        return depth+1;
    }

    /// --- iterative
    private int maxDepth2(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                for (Node child: current.children) {
                    queue.offer(child);
                }
            }
            depth++;
        }
        return depth;
    }

    static class Node {
        public int val;
        public List<Node> children;
    }
}
