package dataStructures.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple binary search tree
 * See #MyBinarySearchTree for more sophisticated BST
 */
public class MyBST {
    private Node root;

    public static void main(String[] args) {
        MyBST myBST = new MyBST();

        myBST.insert(10);
        myBST.insert(30);
        myBST.insert(20);
        myBST.insert(8);
        myBST.insert(0);
        myBST.insert(7);
        myBST.insert(11);
        myBST.insert(12);

        myBST.find(10);
        myBST.find(21);
        myBST.inOrderTraversal();

        myBST.delete(20);
        myBST.find(20);
        myBST.inOrderTraversal();

        System.out.println();
        myBST.levelOrderTraversal();
    }

    private static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
        }
    }

    private void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data > node.data) {
            node.right = insert(node.right, data);
        } else if (data < node.data) {
            node.left = insert(node.left, data);
        } else {
            return node; // data already exists
        }

        return node;
    }

    private void find(int data) {
        find(root, data);
    }

    private void find(Node node, int data) {
        if (node == null) {
            System.out.println("Not Found");
            return;
        }
        if (data == node.data) {
            System.out.println("Found");
            return;
        }
        if (data > node.data) {
            find(node.right, data);
        } else  {
            find(node.left, data);
        }
    }

    private void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.data);
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
            System.out.println(node.data);
        }
    }

    private void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            // delete node
            // three cases
            // case 1 - no child
            if (node.left == null && node.right == null) {
                System.out.println("deleted " + data);
                return null; //delete by returning null
            }

            // case 2 - on child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // case 3 - two children. find smallest from right child
            int smallest = findSmallest(node);
            node.data = smallest;
            node.right = delete(node.right, smallest);
            return node;
        }
        if (data > node.data) {
            node.right = delete(node.right, data);
        } else {
            node.left = delete(node.left, data);
        }

        return node;
    }

    // if left subtree is null, then node is smallest
    private int findSmallest(Node node) {
        if (node.left == null) {
            return node.data;
        } else {
            return findSmallest(node.left);
        }
    }

    // or breadth first in graph
    private void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.data);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
