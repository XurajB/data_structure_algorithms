package problems.tree;

/**
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * Each node will have a reference to its parent node.
 */
public class LowestCommonAncestor3 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    //////
    // similar to IntersectionOfTwoLinkedLists
    // find the intersection of these two nodes.
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null? q : a.parent;
            b = b == null? p : b.parent;
        }
        return a;
    }

    ///////////////
    ////// O(N), O(height)
    public Node lowestCommonAncestor2(Node p, Node q) {
        Node root = p;
        while (root.parent != null) {
            root = root.parent;
        }
        return helper(root, p, q);
    }

    private Node helper(Node root, Node p, Node q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root.val == p.val ? p : q;
        }
        Node left = helper(root.left, p, q);
        Node right = helper(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
}
