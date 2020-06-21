package algorithms.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * O(N) time and space. O(n + N) - N can be dupes/collision in same buckets
 * Can be used to sort items with limited of items that can be in an index
 * like - sort numbers that are within range 0-9
 * If the bucket size increases a lot, that may not be worth it
 * Also called Pigeonhole sort
 * Worse case can be O(n^2) if all elements are assigned to the same bucket
 *
 * Algorithm:
 * bucketSort()
 *   create N buckets each of which can hold a range of values
 *   for all the buckets
 *     initialize each bucket with 0 values
 *   for all the buckets
 *     put elements into buckets matching the range
 *   for all the buckets
 *     sort elements in each bucket
 *   gather elements from each bucket
 * end bucketSort
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] nums = {1,5,7,10,11,4,2,1,0,8,4,6};
        bucketSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    //Given an unordered list of integers, rearrange them in the natural order. Input between 1-12
    private static void bucketSort(int[] nums) {
        List<Integer>[] buckets = new ArrayList[12];

        // create empty buckets
        for (int i = 0; i < nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // add elements into the buckets
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i]; // this needs to be optimized for the array size and input
            buckets[index].add(nums[i]);
        }

        // sort elements in each bucket
        for (int i = 0; i < nums.length; i++) {
            Collections.sort(buckets[i]);
        }

        // get the sorted array
        int index = 0;
        for (int i = 0; i<nums.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                nums[index++] = buckets[i].get(j);
            }
        }
    }
}
