package problems.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * 0 <= i, j < nums.length
 * i != j
 * a <= b
 * b - a == k
 */
public class KDiffPairs {
    public static void main(String[] args) {
        int[] nums = {3,1,4,1,5};
        System.out.println(findPairs(nums, 2));
    }

    // two pointers, starting from left
    private static int findPairs(int[] nums, int k) {
        int count = 0;

        int l = 0;
        int r = 1;
        Arrays.sort(nums);
        while (l < nums.length && r < nums.length) {
            if (l == r || nums[r] - nums[l] < k) {
                r++;
            } else if (nums[r] - nums[l] > k) {
                l++;
            } else {
                count++;

                l++;
                while (l < nums.length && nums[l-1] == nums[l]) {
                    l++;
                }
            }
        }

        return count;
    }

    // O(N)
    private static int findPairs2(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (int key: map.keySet()) {
            int x = map.get(key);

            if (k == 0) {
                // for k = 0 case, if same element appear twice
                if (x >= 2) {
                    ans++;
                }
            } else if (map.containsKey(key + k)) {
                ans++;
            }
        }

        return ans;
    }
}
