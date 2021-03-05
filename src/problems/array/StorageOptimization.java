package problems.array;

import java.util.Arrays;

/**
 * Amazon is experimenting with a flexible storage system for their warehouses. The storage unit consists of shelving system which is one meeter deep with removal
 * vertical and horizontal seperators. When all separators are installed each storage space is one cubic meters. Determine the volume of the largest space when
 * a series of horizintal and vertical separators are removed
 */
public class StorageOptimization {
    public static void main(String[] args) {
    }

    private static int storageOpt(int n, int m, int[] h, int[] v) {
        Arrays.sort(h); // sort so we can check consecutive indexes. Find the longest consecutive sequence
        Arrays.sort(v);

        // find longest consecutive horizontal bars
        int maxH = 1; // size = 1 case
        int seq = 1;
        for (int i = 1; i < h.length; i++) {
            if (h[i] - h[i-1] == 1) {
                seq++;
            } else {
                seq = 1;
            }
            maxH = Math.max(maxH, seq);
        }

        // find longest consecutive vertical bars
        int maxV = 1;
        seq = 1;
        for (int i = 1; i < v.length; i++) {
            if (v[i] - v[i-1] == 1) {
                seq++;
            } else {
                seq = 1;
            }
            maxV = Math.max(maxV, seq);
        }

        return maxH * maxV;
    }
}
