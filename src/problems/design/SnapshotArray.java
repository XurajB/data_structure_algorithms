package problems.design;

import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 * - SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * - void set(index, val) sets the element at the given index to be equal to val.
 * - int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * - int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 */
public class SnapshotArray {
    private TreeMap<Integer, Integer>[] map;
    private int snapId = 0;
    public SnapshotArray(int length) {
        // we can store the history of the array but if we have a large array we store unnecessary data, this is similar to number of times we hit ctrl+s
        // instead of copying the whole array, we only record the changes in a cell
        // when we want to get the value in history, just binary search to the time point - using treemap
        map = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new TreeMap<>(); // snapid, value
            map[i].put(0, 0); // initialize with 0
        }
    }

    public void set(int index, int val) {
        map[index].put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    // log(n)
    public int get(int index, int snap_id) {
        // binary search for floor entry
        // floor entry will return 0 if not found, since we initialized with 0
        // instead we can also return 0 if not found
        return map[index].floorEntry(snap_id).getValue();
    }
}
