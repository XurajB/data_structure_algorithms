package problems.design;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are
 * being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1
 * It is possible that several hits arrive roughly at the same time.
 * https://leetcode.com/problems/design-hit-counter/
 */
public class HitCounter {
    // given time interval is 300
    // use two buckets for timestamp and hits. 1 bucket for every second

    // basic idea is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds.
    // hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time.
    // If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.
    private int[] hits;
    private int[] times;

    // O(1), O(1)
    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }

    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (times[index] == timestamp) {
            // several hits can arrive at the same time
            hits[index]++;
        } else {
            // update time, add count at this new time
            times[index] = timestamp;
            hits[index] = 1;
        }
    }

    // only return past 5 minutes
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) { // past 5 minutes ( > 300 means more than 5 minutes ago)
                total += hits[i];
            }
        }
        return total;
    }
}
