package problems.tree;

import dataStructures.trees.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 */
public class FindDuplicateSubtree {
    // O(N), average case can be O(NlogN) where logN is recursion for balanced tree. O(N^2) worse case
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        serialize(root, new HashMap<>(), ans);
        return ans;
    }

    private String serialize(TreeNode node, Map<String, Integer> map, List<TreeNode> ans) {
        if (node == null) {
            return "*";
        }
        String serial = node.val + "-" + serialize(node.left, map, ans) + "-" + serialize(node.right, map, ans);
        map.put(serial, map.getOrDefault(serial, 0) + 1);

        if (map.get(serial) == 2) {
            ans.add(node);
        }
        return serial;
    }
}
