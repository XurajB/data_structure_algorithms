package dataStructures.trees;

/**
 * Given a binary tree, find its minimum depth.
 * https://www.interviewbit.com/problems/min-depth-of-binary-tree/
 */
public class MinDepthOfBst {
    final int INF=Integer.MAX_VALUE-1;
    public int minDepth(TreeNode A) {
        // if we don't have any node then our solution result in infinite
        if(A==null)return INF;

        // if A is the leaf node then return depth as 1
        if(A.left==null && A.right==null)return 1;
        return 1+Math.min(minDepth(A.left),minDepth(A.right));
    }
}
