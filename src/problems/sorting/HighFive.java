package problems.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
 */
public class HighFive {
    public int[][] highFive(int[][] items) {
        Map<Integer, Score> map = new HashMap<>(); // id, score
        for (int[] item: items) {
            if (!map.containsKey(item[0])) {
                map.put(item[0], new Score());
            }
            map.get(item[0]).add(item[1]);
        }

        int[][] top = new int[map.size()][2];
        int j = 0;
        for (int key: map.keySet()) {
            top[j++] = new int[] {key, map.get(key).getAverage()};
        }

        Arrays.sort(top, (a, b) -> a[0] - b[0]);

        int len = Math.min(5, top.length);
        int[][] ans = new int[len][2];
        System.arraycopy(top, 0, ans, 0, len);

        return ans;
    }

    static class Score {
        int total = 0;
        int count = 0;

        void add(int score) {
            total = total + score;
            count = count + 1;
        }

        int getAverage() {
            return total/count;
        }
    }
}
