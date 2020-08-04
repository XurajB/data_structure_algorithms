package problems.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRooms2 {

    public static void main(String[] args) {
        int[][] intervals = {
                {0, 30},
                {15, 20},
                {5, 10}
        };
        System.out.println(minMeetingRooms(intervals));
    }

    // time: O(nLogn), space: O(n)
    private static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (pq.size() > 0 && interval[0] >= pq.peek()[1]) {
                pq.poll();
            }
            pq.offer(interval);
        }
        return pq.size();
    }
}
