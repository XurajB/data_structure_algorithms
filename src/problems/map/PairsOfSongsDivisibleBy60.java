package problems.map;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 */
public class PairsOfSongsDivisibleBy60 {
    public static void main(String[] args) {
        System.out.println(numPairsDivisibleBy60(new int[] {30,20,150,100,40}));
    }

    private static int numPairsDivisibleBy60(int[] time) {
        // similar to 2 sum, we want to find if we have another number whose sum % 60 = 0
        int[] map = new int[60];
        int count = 0;

        for (int num: time) {
            if (num % 60 == 0) {
                count += map[0];
            } else {
                count += map[60 - num % 60];
            }
            map[num % 60]++;
        }
        return count;
    }
}
