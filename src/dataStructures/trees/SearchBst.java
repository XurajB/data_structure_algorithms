package dataStructures.trees;

public class SearchBst {

    // recursive
    // O(LogN), O(H)
    public TreeNode searchBst(TreeNode root, int val) {
        if (root == null || val == root.val) {
            return root;
        }
        return val < root.val ? searchBst(root.left, val) : searchBst(root.right, val);
    }

    // iterative
    // O(LogN), O(1)
    public TreeNode searchBst2(TreeNode root, int val) {
        while (root != null && val != root.val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}
