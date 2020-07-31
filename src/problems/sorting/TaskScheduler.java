package problems.sorting;

import java.util.Arrays;

/**
 * You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task.
 * Tasks could be done without the original order of the array. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 * You need to return the least number of units of times that the CPU will take to finish all the given tasks.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println(leastInterval(tasks, 2));
    }

    // There must be n unit of time between same task - that can be idle or any other remaining task
    // ["A","A","A","B","B","B"], n = 2
    // there must be 2 unit of time between A (which is the max frequency task) - it could be idle or another task
    // A -> B -> idle -> A -> B -> idle -> A -> B
    // max unit of idle time can be computed: (max freq - 1) * n = 4 in this case because A _ _ A _  _ A, to minimize total unit - we can fill in other tasks or if no more idle
    // O(N)
    private static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        int[] freq = new int[26];
        // calculate frequencies
        for (char task: tasks) {
            freq[task - 'A']++;
        }

        // sort them
        Arrays.sort(freq); // since the size is 26, we can consider this constant time

        // max unit of idle time
        int maxFreq = freq[25];
        int maxIdle = (maxFreq - 1) * n;

        for (int i = freq.length - 2; i >= 0; i--) {
            maxIdle -= Math.min(maxFreq - 1, freq[i]); // we only have space for maxFreq - 1 in between max item (can't put more that one other tasks in the same spot)
        }

        maxIdle = Math.max(0, maxIdle);
        return tasks.length + maxIdle;
    }
}
