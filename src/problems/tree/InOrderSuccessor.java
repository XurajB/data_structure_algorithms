package problems.tree;

import dataStructures.trees.TreeNode;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 */
public class InOrderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode node = root;
        TreeNode successor = null;

        while (node != null) {
            if (node.val > p.val) {
                successor = node; // greater than p, potential successor. but we will still continue if we find anything smaller than this
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

    // IF WE ARE NOT GIVEN ROOT NODE, but with a node with parent pointer
    public Node inorderSuccessor(Node node) {
        // if it has right subtree, successor is somewhere lower
        // case 1
        Node temp = node;
        if (temp.right != null) {
            temp = temp.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }
        // case 2
        // its successor is upper in the tree
        //   4
        //  2  7
        //   3  <-
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
