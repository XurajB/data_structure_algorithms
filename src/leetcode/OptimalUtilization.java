package leetcode;

import java.util.*;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value.
 * Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible.
 * Return a list of ids of selected elements. If no pair is possible, return an empty list.
 * https://leetcode.com/discuss/interview-question/373202
 */
public class OptimalUtilization {
    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1, 3}, {2, 5}, {3, 7}, {4, 10}
        };
        int[][] b = new int[][] {
                {1, 2}, {2, 3}, {3, 4}, {4, 5}
        };
        int target = 10;

        int[][] a2 = new int[][] {
                {1, 8}, {2, 15}, {3, 9}
        };
        int[][] b2 = new int[][] {
                {1, 8}, {2, 11}, {3, 12}
        };
        int target2 = 20;

        List<int[]> ans = getPairs(a2, b2, target2);
        for (int[] pair: ans) {
            System.out.println(Arrays.toString(pair));
        }
    }

    private static List<int[]> getPairs(int[][] a, int[][] b, int target) {
        Map<Integer, List<int[]>> sumMap = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int sum = a[i][1] + b[j][1];
                if (sum <= target) {
                    List<int[]> currentPairs = sumMap.getOrDefault(sum, new ArrayList<>());
                    currentPairs.add(new int[] {a[i][0], b[j][0]}); // store pairs
                    sumMap.put(sum, currentPairs);

                    max = Math.max(max, sum);
                }
            }
        }

        if (sumMap.get(max) != null && sumMap.get(max).size() > 0) {
            return sumMap.get(max);
        }
        return new ArrayList<>();
    }
}
