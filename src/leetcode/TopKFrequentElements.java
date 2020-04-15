package leetcode;

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

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.count - o2.count; // we want min heap because we want to keep the size at k. other wise it would be a max heap
            }
        });

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
}
