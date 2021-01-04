package problems.array;

import java.util.*;

/**
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order
 */
public class DiagonalTraverse2 {
    public static void main(String[] args) {
    }

    // O(m*n)
    private int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        int maxSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) {
                int index = i + j;
                map.putIfAbsent(index, new LinkedList<>());
                // same diagonal elements (i + j) have same sum
                map.get(index).addFirst(row.get(j)); // if we are going the other way, then add instead of addFirst
                maxSum = Math.max(maxSum, index); // we need this later to iterate through map
            }
        }

        // find the max sum, which will be the last col, last row and last in our answer
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= maxSum; i++) {
            list.addAll(map.get(i));
        }

        // generate final array
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
