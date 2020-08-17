package problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * What is the total amount of fruit you can collect with this procedure?
 */
public class FruitIntoBaskets {
    public static void main(String[] args) {
        int[] tree = {1,2,3,2,2};
        System.out.println(totalFruit(tree));
    }

    // O(N), O(2)
    private static int totalFruit(int[] tree) {
        int end = 0;
        int start = 0;
        int count = Integer.MIN_VALUE;
        Map<Integer, Integer> picked = new HashMap<>();
        while (end < tree.length) {
            int fruit1 = tree[end];
            picked.put(fruit1, picked.getOrDefault(fruit1, 0)+1);
            while (start < end && picked.size() > 2) { // basket size is 2
                int fruit2 = tree[start];
                picked.put(fruit2, picked.get(fruit2)-1);

                if (picked.get(fruit2) == 0) {
                    picked.remove(fruit2);
                }

                start++;
            }
            count = Math.max(count, end-start+1);
            end++;
        }
        return count;
    }
}
