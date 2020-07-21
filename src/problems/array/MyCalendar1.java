package problems.array;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
 * the range of real numbers x such that start <= x < end.
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking.
 * Otherwise, return false and do not add the event to the calendar.
 */
public class MyCalendar1 {
    public static void main(String[] args) {

    }
    // O(N^2) if we have n number of slots to enter, each time it takes n time to book
    List<int[]> slots = new ArrayList<>();
    public boolean book(int start, int end) {
        for (int[] slot: slots) {
            if (end > slot[0] && start < slot[1]) {
                return false;
            }
        }
        slots.add(new int[] {start, end});
        return true;
    }

    //------------------
    // O(NlogN)
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public boolean book2(int start, int end) {
        Integer low = map.lowerKey(end); //Returns the greatest key strictly less than the given key, or null if there is no such key.
        // this mean get start time before this end time
        // if 10, 20 is in the tree
        // we are inserting 15, 20 (which is invalid)
        // lowerkey of 20 would get back 10
        // so low is 20. which is not less than 15 so can't do it. if it was 12 which is less than 15, then we could
        if (low == null || map.get(low) <= start) {
            map.put(start, end);
            return true;
        }
        return false;
    }

}
