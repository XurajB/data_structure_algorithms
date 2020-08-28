package problems.sorting;

import java.util.Arrays;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 */
public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        int[] start = {1,2,3,3};
        int[] end = {3,4,5,6};
        int[] profit = {50,10,40,70};
        System.out.println(jobScheduling(start, end, profit));
    }

    //greedy - local optimization
    //dp - global optimization
    private static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        // sort by end time
        Arrays.sort(jobs, (a, b) -> a.endTime - b.endTime);
        // dp[i] = max profit up to i
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            // we have two choices
            // include ith job or not to include ith job
            // case 1 include this job
            int p = jobs[i].profit;
            // do binary search to find latest job that does not conflict with this job
            // that that job exists - take which ever is max dp[index] + p, dp[i-1] (case 2)
            // we simply cannot take last job (i-1) because that job might has started before this start time (so still overlapping)
            //   [  ]
            //  [  ]
            int index = search(jobs, i);
            if (index != -1) {
                p += dp[index];
            }

            // store max
            dp[i] = Math.max(p, dp[i-1]);
        }

        return dp[n-1];
    }

    // binary search  since the array is sorted
    // find last non overlapping job with i
    private static int search(Job[] jobs, int i) {
        int lo = 0;
        int high = i - 1;
        while (lo <= high) {
            int mid = lo + (high - lo)/2;
            // if mid does not overlap with current job (i), then there may be others that do not overlap to its right
            if (jobs[mid].endTime <= jobs[i].startTime) {
                // check if next job overlaps
                if (jobs[mid + 1].endTime <= jobs[i].startTime) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            } else {
                // it overlaps, look left side to find non overlap
                high = mid - 1;
            }
        }
        // if all jobs overlap
        return -1;
    }

    static class Job {
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }
}
