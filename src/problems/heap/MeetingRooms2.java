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
        if (intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> allocator = new PriorityQueue<>(
                intervals.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        allocator.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {

            // previous meeting has ended (current start is higher than last meeting end time). so we don't need a room
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }
            allocator.add(intervals[i][1]);
        }

        return allocator.size();
    }
}
