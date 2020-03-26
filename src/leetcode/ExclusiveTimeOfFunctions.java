package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Return the exclusive time of each function, sorted by their function id.
 * https://leetcode.com/problems/exclusive-time-of-functions/
 */
public class ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");

        System.out.println(Arrays.toString(exclusiveTime(2, logs)));
    }

    private static int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> jobs = new Stack<>();

        String[] log1 = logs.get(0).split(":");
        jobs.push(Integer.parseInt(log1[0]));

        int prev = Integer.parseInt(log1[2]);

        for (int i = 1; i < logs.size(); i++) {
            log1 = logs.get(i).split(":");
            if (log1[1].equals("start")) {
                if (!jobs.isEmpty()) {
                    ans[jobs.peek()] += Integer.parseInt(log1[2]) - prev;
                }
                jobs.push(Integer.parseInt(log1[0]));
                prev = Integer.parseInt(log1[2]);
            } else {
                ans[jobs.peek()] += Integer.parseInt(log1[2]) - prev + 1;
                jobs.pop();
                prev = Integer.parseInt(log1[2]) + 1;
            }
        }

        return ans;
    }
}
