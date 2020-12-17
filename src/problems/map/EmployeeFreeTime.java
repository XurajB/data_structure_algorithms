package problems.map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 */
public class EmployeeFreeTime {
    public static void main(String[] args) {

    }

    // O(NLogN)
    private static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ans = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));
        // add all intervals to pq
        schedule.forEach(interval -> pq.addAll(interval));

        int end = pq.poll().end;
        while (!pq.isEmpty()) {
            Interval interval = pq.poll();
            if (end < interval.start) {
                ans.add(new Interval(end, interval.start));
                end = interval.end;
            } else {
                // merge
                end = Math.max(end, interval.end);
            }
        }

        return ans;
    }

    static class Interval {
        public int start;
        public int end;
        public Interval() {}
        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
}
