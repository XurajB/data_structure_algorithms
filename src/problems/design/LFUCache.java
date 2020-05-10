package problems.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {
    Map<Integer, Integer> values; // key value
    Map<Integer, Integer> counts; // key frequency
    Map<Integer, LinkedHashSet<Integer>> lists; // frequency, keys (in ordered form, so we can evict LRU key)

    int min; // keep track of current min frequency
    int capacity;

    // all operations on O(1)
    // space: O(n)
    LFUCache(int capacity) {
        this.capacity = capacity;
        this.min = 0;

        values = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }
        update(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        // if exists, update value and frequency map
        if (values.containsKey(key)) {
            values.put(key, value);
            update(key);
            return;
        }
        if (values.size() >= capacity) {
            evict();
        }
        values.put(key, value);
        counts.put(key, 1);
        addToList(1, key);
        min = 1; // add new key, so new min frequency is 1
    }

    private void evict() {
        // first one in the list was first inserted (since the list is ordered)
        int key = lists.get(min).iterator().next();
        lists.get(min).remove(key);

        // remove from other maps
        values.remove(key);
        counts.remove(key);
    }

    private void update(int key) {
        // update count
        int count = counts.get(key);
        counts.put(key, count + 1);

        // remove from current frequency bucket
        lists.get(count).remove(key);

        // if current count bucket is 0, then increment min
        if (count == min && lists.get(count).size() == 0) {
            min++;
        }

        // add updated count to the list
        addToList(count + 1, key);
    }

    private void addToList(int count, int key) {
        if (!lists.containsKey(key)) {
            lists.put(key, new LinkedHashSet<>());
        }
        lists.get(count).add(key);
    }
}
