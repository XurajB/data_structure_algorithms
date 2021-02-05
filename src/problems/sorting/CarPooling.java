package problems.sorting;

import java.util.*;

/**
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip:
 * the number of passengers that must be picked up, and the locations to pick them up and drop them off.
 * The locations are given as the number of kilometers due east from your vehicle's initial location.
 *
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 */
public class CarPooling {
    public static void main(String[] args) {
        int[][] trips = {{2,1,5},{3,3,7}};
        System.out.println(carPooling(trips, 4));
    }

    // O(nlogn)
    private static boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // end, num_passengers
        int size = 0;
        for (int i = 0; i < trips.length; i++) {
            while (pq.size() > 0 && pq.peek()[0] <= trips[i][1]) {
                size = size - pq.poll()[1];
            }
            size = size + trips[i][0];
            if (size > capacity) {
                return false;
            }
            pq.offer(new int[] {trips[i][2], trips[i][0]});
        }
        return true;
    }

    // O(nlogn)
    // save time points and change on current capacity
    // sort all the changes on the key of time points
    // track the current capacity and return false if over capacity
    private static boolean carPooling2(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>(); //
        int size = 0;
        for (int[] trip: trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }
        for (int i: map.values()) {
            size = size + i;
            if (size > capacity) {
                return false;
            }
        }
        return true;
    }
}
