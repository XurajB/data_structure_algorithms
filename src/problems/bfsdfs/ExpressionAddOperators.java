package problems.bfsdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        System.out.println(addOperators("105", 5));
    }

    private static List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(ans, "", num.toCharArray(), 0, target, 0, 0);
        return ans;
    }

    // remember to use long, int can overflow quickly
    // for multiplication, if you have a sequence of 12345 and you have proceeded to 1 + 2 + 3, now your eval is 6 right? If you want
    // to add a * between 3 and 4, you would take 3 as the digit to be multiplied,
    // so you want to take it out from the existing eval. You have 1 + 2 + 3 * 4 and the eval now is (1 + 2 + 3) - 3 + (3 * 4). we already have 1+2+3, but mult happens between 3*4
    // O(4^N) - there are 4 operations (noop, +,-,*)
    private static void dfs(List<String> ans, String expr, char[] nums, int index, int target, long calcVal, long prev) {
        if (index == nums.length) {
            if (target == calcVal) {
                ans.add(expr);
            }
            return;
        }
        long curr = 0;
        // loop because we have try every combination of nums (105 = 1, 5, 10, 105). See TargetSum
        for (int i = index; i < nums.length; i++) {
            // corner case: if current num is 0, we can only use it as a single digit number
            // if it is not a single digit number with leading 0, it should be considered as an invalid number
            if (nums[index] == '0' && i != index) {
                continue;
            }
            curr = 10 * curr + nums[i] - '0';
            if (index == 0) {
                // first one, so no calculation (no-op)
                dfs(ans, expr + curr, nums, i+1, target, curr, curr);
            } else {
                dfs(ans, expr + "+" + curr, nums, i+1, target, calcVal + curr, curr);
                dfs(ans, expr + "-" + curr, nums, i+1, target, calcVal - curr, -curr);
                dfs(ans, expr + "*" + curr, nums, i+1, target, calcVal - prev + prev * curr, prev * curr);
            }
        }
    }
}
