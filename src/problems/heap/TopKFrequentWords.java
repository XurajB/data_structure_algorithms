package problems.heap;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] input = new String[] {"i", "love", "problems", "i", "love", "coding", "love"};
        List<String>  ans = topKFrequent(input, 2);
        System.out.println(ans);
    }

    // NLogK, N
    // NLogN if max heap in this case
    private static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        // min heap to keep max elements so we can poll when the size is over k
        PriorityQueue<String> heap = new PriorityQueue<>(k, (s1, s2) -> {
            if (count.get(s1).equals(count.get(s2))) {
                return s2.compareTo(s1);
            } else {
                return count.get(s1) - count.get(s2);
            }
        });

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll(); // min heap for this reason
            }
        }

        List<String> ans = new ArrayList<>();
        while (!heap.isEmpty()) {
            ans.add(heap.poll());
        }
        Collections.reverse(ans);
        return ans;
    }

    // O(nlogn), O(n)
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> ans = new ArrayList<>(count.keySet());
        Collections.sort(ans, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                if (count.get(s1).equals(count.get(s2))) {
                    return s1.compareTo(s2);
                } else {
                    return count.get(s2) - count.get(s1);
                }
            }
        });
        return ans.subList(0, k);
    }
}
