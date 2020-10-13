package problems.design;

import java.util.TreeMap;

/**
 * A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.
 *
 * addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval.
 * Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
 * removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
 */
public class RangeModule {
    public static void main(String[] args) {

    }

    TreeMap<Integer, Integer> map;
    public RangeModule() {
        map = new TreeMap<>();
    }

    // [ = inclusive, ) = less than
    public void addRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);

        if (start != null && map.get(start) >= left) {
            left = start;
        }
        if (end != null && map.get(end) > right) {
            right = map.get(end);
        }

        map.put(left, right);

        // clean up intermediate intervals
        map.subMap(left, false, right, true).clear();
    }

    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if (start == null) {
            return false;
        }
        return map.get(start) >= right;
    }

    public void removeRange(int left, int right) {

        // ex: in map: 10 - 20, remove 15, 17
        // put: 10,15 and 17-20

        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);

        // end should come first
        if (end != null && map.get(end) > right) {
            map.put(right, map.get(end));
        }

        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }

        // clean intermediate
        map.subMap(left, true, right, false).clear();
    }
}
