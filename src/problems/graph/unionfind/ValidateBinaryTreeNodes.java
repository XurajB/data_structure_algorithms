package problems.graph.unionfind;

/**
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 */
public class ValidateBinaryTreeNodes {
    public static void main(String[] args) {
        System.out.println(validateBinaryTreeNodes(4, new int[] {1,-1,3,-1}, new int[] {2,-1,-1,-1}));
    }

    // E + V
    private static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < leftChild.length; i++) {
            // if parent and child have same root, then it creates a cycle
            if (leftChild[i] != -1 && find(parents, leftChild[i]) == find(parents, i)) {
                return false;
            }
            if (rightChild[i] != -1 && find(parents, rightChild[i]) == find(parents, i)) {
                return false;
            }
            // do the union
            if (leftChild[i] != -1) {
                union(parents, leftChild[i], i);
            }
            if (rightChild[i] != -1) {
                union(parents, rightChild[i], i);
            }
        }
        // all same trees are merged, if we have a node that still has it as parent then it is disjoint
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                count++;
            }
        }
        return count == 1;
    }

    private static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        }
        parents[x] = find(parents, parents[x]);
        return parents[x];
    }

    private static void union(int[] parents, int x, int y) {
        int px = find(parents, x);
        int py = find(parents, y);

        if (px != py) {
            parents[x] = y; // make y parent of x
        }
    }
}
