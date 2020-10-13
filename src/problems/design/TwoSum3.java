package problems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that accepts integers of a stream, and checks if it has a pair of integers that sum up to a particular value.
 *
 * Implement a TwoSum class:
 *
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 * void add(int number) Adds number to the data structure.
 * boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 */
public class TwoSum3 {
    Map<Integer, Integer> data;

    /** Initialize your data structure here. */
    public TwoSum3() {
        data = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        data.put(number, data.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num: data.keySet()) {
            int comp = value - num;

            if (data.containsKey(comp)) {

                // if same number
                if (comp == num) {
                    if (data.get(comp) >= 2) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
