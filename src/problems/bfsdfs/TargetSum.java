package problems.bfsdfs;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[] {1,1,1,1,1}, 3));
    }

    // O(n * s)
    private static int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        return helper(nums, 0, 0, S, new HashMap<>());
    }

    private static int helper(int[] nums, int index, int curSum, int target, Map<String, Integer> map){
        String encodeString = index + "->" + curSum;

        if (map.containsKey(encodeString)){
            return map.get(encodeString);
        }

        if (index == nums.length){
            if (curSum == target){
                return 1;
            } else {
                return 0;
            }
        }

        int curNum = nums[index];

        int add = helper(nums, index + 1, curSum - curNum, target, map);
        int minus = helper(nums, index + 1, curSum + curNum, target, map);

        map.put(encodeString, add + minus);
        return add + minus;
    }
}
