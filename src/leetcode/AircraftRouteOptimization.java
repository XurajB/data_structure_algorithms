package leetcode;

import java.util.*;

/**
 * Each aircraft should be assigned two shipping routes at once: one forward route and one return route.
 * Due to the complex scheduling of flight plans,all aircraft have a fixed maximum operating travel distance,
 * and cannot be scheduled to fly a shipping route that requires more travel distance than the prescribed maximum operating travel distance.
 * The goal of the system is to optimize the total operating travel distance of a given aircraft.
 * A forward/return shipping route pair is considered to be “optimal” if there does not exist another pair that has a higher operating travel distance than this pair,
 * and also has a total less than or equal to the maximum operating travel distance of the aircraft.
 *
 * For example, if the aircraft has a maximum operating travel distance of 3000 miles,
 * a forward/return shipping route pair using a total of 2900 miles would be optimal if there does not exist a pair that uses a total operating travel distance of 3000 miles,
 * but would not be considered optimal if such a pair did exist.
 * Your task is to write an algorithm to optimize the sets of forward/return shipping route pairs that allow the aircraft to be optimally utilized,
 * given a list of forward shipping routes and a list of return shipping routes.
 * https://leetcode.com/discuss/interview-question/582530/Amazon-or-OA-2020-or-Aircraft-Route-Optimization
 */
public class AircraftRouteOptimization {

    public static void main(String[] args) {
        int maxTravel = 7000;
        int[][] forward = {{1,2000}, {2,4000}, {3, 6000}};
        int[][] back = {{1,2000}};

        int maxTravel2 = 10000;
        int[][] forward2 = {{1,3000}, {2,5000}, {3, 7000}, {4, 10000}};
        int[][] back2 = {{1,2000}, {2,3000}, {3, 4000}, {4, 5000}};

        List<int[]> ans = getOptimizedRoute(maxTravel2, forward2, back2);
        for (int[] a: ans) {
            System.out.println(Arrays.toString(a));
        }
    }

    private static List<int[]> getOptimizedRoute(int maxDistance, int[][] forwardRouteList, int[][] returnRouteList) {
        Map<Integer, List<int[]>> map = new HashMap<>();

        // sort by min distance
        Arrays.sort(forwardRouteList, (a, b) -> a[1] - b[1]);
        Arrays.sort(returnRouteList, (a, b) -> a[1] - b[1]);

        int i = 0;
        int j = returnRouteList.length - 1; // return list is smaller than forward list

        while (i < forwardRouteList.length && j >= 0) {
            int diff = maxDistance - (forwardRouteList[i][1] + returnRouteList[j][1]);
            if (diff >= 0) {
                if (!map.containsKey(diff)) {
                    map.put(diff, new ArrayList<>());
                }
                map.get(diff).add(new int[] {forwardRouteList[i][0], returnRouteList[j][0]});
                i++;
            } else {
                j--;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int key: map.keySet()) {
            min = Math.min(min, key);
        }

        return map.get(min);
    }
}
