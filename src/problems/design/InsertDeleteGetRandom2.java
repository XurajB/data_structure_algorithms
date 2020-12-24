package problems.design;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements.
 * The probability of each element being returned is linearly related to the number of same value the collection contains.
 */
public class InsertDeleteGetRandom2 {
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random random;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandom2() {
        map = new HashMap<Integer, Set<Integer>>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, (Set<Integer>) new HashSet());
        }
        map.get(val).add(list.size());
        list.add(val);

        return true;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).size() == 0) {
            return false;
        }

        // got index
        int index = map.get(val).iterator().next();
        map.get(val).remove(index);

        int lastItem = list.get(list.size()-1);

        list.set(index, lastItem);

        map.get(lastItem).add(index);
        map.get(lastItem).remove(list.size() - 1);

        list.remove(list.size()-1);

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
