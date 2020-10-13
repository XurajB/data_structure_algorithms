package problems.tree;

import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class SerializeAndDeserializeNary {
    // In binary tree, we knew it only had two children and next two could be the left and right children (whether null or not)
    // In n-ary, we need to encode that information. there is no null except for root, because children count could be 0 but no null children

    // recursive
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }

        StringBuilder str = new StringBuilder(root.val + ",");
        str.append(root.children.size()).append(",");
        for (Node node: root.children) {
            str.append(serialize(node));
        }

        return str.toString();
    }

    public Node deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] splits = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(splits));
        return deserializeHelper(queue);
    }

    private Node deserializeHelper(Queue<String> queue) {
        Node node = new Node(Integer.parseInt(queue.poll()));
        int size = Integer.parseInt(queue.poll());

        node.children = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            node.children.add(deserializeHelper(queue));
        }

        return node;
    }

    // another way to do this is by passing reference
    public Node deserialize2(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] splits = data.split(",");
        int[] index = new int[1];
        return deserializeHelper2(splits, index);
    }

    private Node deserializeHelper2(String[] splits, int[] index) {
        if (index[0] == splits.length) {
            return null;
        }
        Node node = new Node(Integer.parseInt(splits[index[0]]));
        index[0]++;
        int size = Integer.parseInt(splits[index[0]]);
        index[0]++;
        node.children = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            node.children.add(deserializeHelper2(splits, index));
        }

        return node;
    }

    // n-ary tree node
    static class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
