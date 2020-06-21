package problems.math;

/**
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
 * [2, 3] - picking 0 (index of 2) has probability of 2 out of 5. AKA sampling with weight
 */
public class RandomPickWithWeight {
    int[] prefixSums;
    int total;
    RandomPickWithWeight(int[] w) {
        int prefixSum = 0;
        prefixSums = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            prefixSum += w[i];
            prefixSums[i] = prefixSum;
        }
        total = prefixSum;
    }

    public int pickIndex() {
        double target = total * Math.random(); // Math.random returns value from 0.0 to 1.0

        // we know prefixSums is sorted, we can do binary search
        int low = 0;
        int high = prefixSums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target > prefixSums[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
