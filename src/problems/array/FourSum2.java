package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 */
public class FourSum2 {
    // O(N^2)
    private static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int ab = A[i] + B[j];
                map.put(ab, map.getOrDefault(ab, 0) + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int cd = -(C[i] + D[j]);
                count += map.getOrDefault(cd, 0);
            }
        }
        return count;
    }
}
