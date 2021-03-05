package problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class OptimizingBoxWeight {
    public static void main(String[] args) {

    }

    private static List<Integer> getOptimizedBoxWeights(int[] arr) {
        Arrays.sort(arr);
        List<Integer> ans = new ArrayList<>();

        int totalSum = Arrays.stream(arr).sum();

        int partition = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            partition += arr[i];
            ans.add(0, arr[i]);

            if (partition > totalSum / 2) {
                break;
            }
        }
        return ans;
    }
}
