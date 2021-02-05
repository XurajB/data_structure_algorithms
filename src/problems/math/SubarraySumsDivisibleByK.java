package problems.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 */
public class SubarraySumsDivisibleByK {

    public static void main(String[] args) {
        System.out.println(subarraysDivByK(new int[] {4,5,0,-2,-3,1}, 5));
    }

    // O(N)
    private static int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for(int a : A) {
            sum += a;
            if (K != 0) {
                sum = sum % K;
            }

            if (sum < 0)  {
                sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4. there is no negative reminder
            }
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
