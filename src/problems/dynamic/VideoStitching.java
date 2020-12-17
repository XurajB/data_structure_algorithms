package problems.dynamic;

import java.util.Arrays;

/**
 * You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].
 * We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 *
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).
 * If the task is impossible, return -1.
 */
public class VideoStitching {
    public static void main(String[] args) {
        int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        System.out.println(videoStitching(clips, 10));
    }

    //TODO: fixme
    private static int videoStitching(int[][] clips, int T) {
        // sort the clip by start time
        // a[i][0] = left, a[i][1] right. so we have left-right range
        Arrays.sort(clips, (a, b) -> a[0] - b[0]);

        if (clips[clips.length - 1][1] < T) {
            return -1; // can't reach end
        }

        // now the problem becomes similar to #JumpGame2 or #MinimumNumberOfTaps
        int curEnd = 0;
        int cuts = 0;

        for (int i = 0; i < clips.length; i++) {
            if (clips[i][0] > curEnd) {
                return -1;
            }
            int maxEnd = curEnd;
            while (i < clips.length && clips[i][0] <= curEnd) {
                maxEnd = Math.max(maxEnd, clips[i][1]);
                i++;
            }

            cuts++;
            curEnd = maxEnd;
            if (curEnd >= T) {
                return cuts;
            }
        }

        return -1;
    }
}
