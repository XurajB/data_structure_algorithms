package problems.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */
public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        System.out.println(kSmallestPairs(nums1, nums2, 4));
    }
    // O(klogk)
    private static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        // min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - (b[0] + b[1])); // num1, num2, index_num2
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return ans;
        }
        // get 1st k pairs into the queue, offer potential better pairs by adding index (0, i) since they are sorted
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[] {nums1[i], nums2[0], 0});
        }
        // if there are not enough pairs, return all pairs
        for (int i = 0; i < Math.min(nums1.length*nums2.length, k); i++) {
            int[] cur = pq.poll();
            ans.add(Arrays.asList(cur[0], cur[1]));
            // we added best pairs for nums1, there could be better pairs for nums2
            // check of bound
            if (cur[2] < nums2.length - 1) { // - 1 so we don't overflow
                int index = cur[2] + 1;
                pq.offer(new int[] {cur[0], nums2[index], index});
            }
        }
        return ans;
    }
}
