package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(increasingCount(new int[] {100,10,50,60,20,90,110})));

    }

    private static int[] increasingCount(int[] input) {
        Stack<int[]> stack = new Stack<>();
        int[] ans = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] < input[i]) {
                span += stack.pop()[1];
            }
            ans[i] = span;
            stack.push(new int[] {input[i], span});
        }
        return ans;
    }

    public static int missingElement(int[] nums, int k) {

        int r = 0;
        int l = 0;


        while (r < nums.length - 1 && k > 0) {
            if (nums[r+1] != nums[r] + 1) {
                int diff = nums[r+1] - nums[r] - 1;
                k = k - diff;

                if (k <= 0) {
                    return nums[r] + 1;
                }
            }
            r++;
        }

        return nums[nums.length - 1] + k;

    }

    public static long taskOfPairing(List<Long> freq) {
        long ans = 0;

        long last = 0;
        for (int i = 1; i < freq.size(); i++) {
            long cur = freq.get(i) + last;
            ans += cur / 2;
            last = cur % 2;
        }

        return ans;
    }

    private static int quiz(int n) {
        if (n == 0) {
            return 1;
        }
        return quiz(n-1) + quiz(n-1);
    }
}
