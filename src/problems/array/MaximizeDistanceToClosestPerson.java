package problems.array;

/**
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to the closest person.
 */
public class MaximizeDistanceToClosestPerson {
    public static void main(String[] args) {
        System.out.println(maxDistToClosest(new int[] {1,0,0,0,1,0,1}));
    }

    private static int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) {
            return 0;
        }
        int count = 0;
        int dist = 0;
        // there are 3 cases: seat on the left, seat in the middle, seat in the right
        while (seats[dist] == 0) {
            dist++; // seat on the left
        }
        for (int i = dist+1; i < seats.length; i++) {
            if (seats[i] == 1) {
                dist = Math.max(dist, (count + 1) / 2); // middle between two person
                count = 0;
            } else {
                count = count + 1;
            }
        }

        return Math.max(count, dist); // right
    }
}
