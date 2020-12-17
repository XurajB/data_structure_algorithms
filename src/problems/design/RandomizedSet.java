package problems.design;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
public class RandomizedSet {
    // to implement such a structure, we have two candidates with O(1) average insert - HashMap, ArrayList
    // HashMap provides insert and delete in average constant time, problems with get random

    //O(N) in the worst-case scenario when the operation exceeds the capacity of currently allocated array/hashmap and invokes space reallocation.
    //space: O(N)

    Random rand = new Random();
    Map<Integer, Integer> dict; // val, index
    List<Integer> list;
    public RandomizedSet() {
        dict = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    // insert into dictionary O(1)
    // append to list: O(1)
    public boolean insert(int val) {
        if (dict.containsKey(val)) {
            return false;
        }
        dict.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    // retrieve an index from map to delete
    // move the last element to that index - O(1)
    // remove the last element O(1)
    public boolean remove(int val) {
        if (!dict.containsKey(val)) {
            return false;
        }
        int currentIndex = dict.get(val);
        int lastElement = list.get(list.size() - 1);

        // set last element into current index
        dict.put(lastElement, currentIndex);
        list.set(currentIndex, lastElement);

        // delete last element
        dict.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    // O(1)
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
