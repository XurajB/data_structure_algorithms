package problems.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 *
 * For each integer in this list:
 *
 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 * The units digit represents the value V of this node, 0 <= V <= 9.
 * Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.
 *
 * It's guaranteed that the given list represents a valid connected binary tree.
 */
public class PathSum4 {
    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> tree = new HashMap<>();
        // each node is represented by a number. 1st digit is level, 2nd is position in that level(starts from 1), 3rd is value
        for (int num: nums) {
            int key = num / 10;
            int value = num % 10;
            tree.put(key, value);
        }

        return helper(nums[0] / 10, 0, tree);
    }

    private int helper(int root, int preSum, Map<Integer, Integer> tree) {

        int sum = 0;

        int level = root / 10;
        int pos = root % 10;
        int left = (level + 1) * 10 + pos * 2 - 1; // level and pos start at 1 instead of 0
        int right = (level + 1) * 10 + pos * 2;

        preSum = tree.get(root) + preSum;

        // check if this is root
        if (!tree.containsKey(left) && !tree.containsKey(right)) {
            sum += preSum;
            return sum;
        }

        if (tree.containsKey(left)) {
            sum += helper(left, preSum, tree);
        }
        if (tree.containsKey(right)) {
            sum += helper(right, preSum, tree);
        }

        return sum;
    }
}
