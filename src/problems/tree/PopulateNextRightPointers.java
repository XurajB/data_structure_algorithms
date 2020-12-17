package problems.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointers {
    // O(N), O(N)
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node last = null;
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (last != null) {
                    last.next = cur;
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                last = cur;
            }
        }
        return root;
    }

    // O(N), O(1)
    public Node connect2(Node root) {
        Node first = root;
        Node dummy = new Node(0);
        Node pre = dummy;
        while(first!=null){
            while(first!=null){
                if(first.left!=null) {pre.next = first.left; pre = pre.next;}
                if(first.right!=null) {pre.next = first.right; pre = pre.next;}
                first = first.next;
            }
            first = dummy.next;
            dummy.next = null;
            pre = dummy;
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
