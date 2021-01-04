package problems.heap;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,2,2,3,4,4};
        System.out.println(topKFrequent(nums, 3));
    }

    // bucket sort
    // O(N)
    private static List<Integer> topKFrequent(int[] nums, int k) {
        // freq map
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        // bucket sort on freq
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
        for (int key : freq.keySet()) {
            bucket[freq.get(key)].add(key);
        }
        // gather result
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            res.addAll(bucket[i]);
            if (res.size() >= k) break;
        }
        return res;
    }

    // O(nLogk), space: O(N)
    private static List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));

        for (int i: map.keySet()) {
            pq.offer(new Node(i, map.get(i)));
            if (pq.size() > k) {
                pq.poll(); // throw away least frequent element
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().value);
        }

        Collections.reverse(ans);
        return ans;
    }

    static class Node {
        int value;
        int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //<<<<<<< QUICK SELECT <<<<<<<<<
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // O(N) Average, O(N^2) Worst
    private int[] unique; // uniques from nums array. we will use the frequency to sort this array
    private Map<Integer, Integer> count;

    private int[] topKFrequent3(int[] nums, int k) {
        count = new HashMap<>();

        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num: count.keySet()) {
            unique[i++] = num;
        }

        // kth top frequent element is (n-k)th least frequent element
        // quick select will do a partial sort, from least frequent to most frequent
        // until (n-k)th least frequent element takes its place (n-k) in a sorted array
        // all element on the left are less frequent, all element on the right are more frequent
        quickSelect(0, n-1, n-k);

        // all elements to the right of n-k are top k frequent
        int[] ans = new int[k];
        int x = 0;
        for (int j = n-k; j < n; j++) {
            ans[x++] = unique[j];
        }
        return ans;
        // return Arrays.copyOfRange(unique, n - k, n);
    }

    private void quickSelect(int left, int right, int k) {
        // only one element
        if (left == right) {
            return;
        }

        Random random = new Random();
        int pivot = left + random.nextInt(right - left);

        pivot = partition(left, right, pivot);
        if (pivot == k) {
            return;
        } else if (k < pivot) {
            quickSelect(left, pivot - 1, k); // go left
        } else {
            quickSelect(pivot + 1, right, k); // go right
        }
    }

    private int partition(int left, int right, int pivotIndex) {
        int pivotFreq = count.get(unique[pivotIndex]); // pivot frequency
        // move pivot to the end
        swap(pivotIndex, right);

        int leftIndex = left;
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivotFreq) {
                swap(leftIndex, i);
                leftIndex++;
            }
        }

        // move pivot to its final place
        swap(leftIndex, right);
        return leftIndex;
    }

    private void swap(int i, int j) {
        int temp = unique[i];
        unique[i] = unique[j];
        unique[j] = temp;
    }

}
