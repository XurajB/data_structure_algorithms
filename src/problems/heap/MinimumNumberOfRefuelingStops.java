package problems.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 */
public class MinimumNumberOfRefuelingStops {
    public static void main(String[] args) {
        int[][] stations = {{10,60},{20,30},{30,30},{60,40}};
        System.out.println(minRefuelStops(100, 10, stations));
    }
    // O(NlogN)
    private static int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int distance = startFuel;
        int stops = 0;

        for (int[] station: stations) {
            // check if we can reach this station, if not fill up from previous station with most gas
            // redo this operation until we have enough gas to reach this station
            while (distance < station[0]) {
                if (pq.isEmpty()) {
                    return -1;
                }
                distance += pq.poll();
                stops++;
            }
            pq.offer(station[1]);
        }

        // this is the last station, check if we can reach the target
        while (distance < target) {
            if (pq.isEmpty()) {
                return -1;
            }
            distance += pq.poll();
            stops++;
        }

        return stops;
    }
}
