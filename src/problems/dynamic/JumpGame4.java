package problems.dynamic;

import java.util.*;

/**
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 */
public class JumpGame4 {
    public static void main(String[] args) {
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(minJumps(arr));
    }

    // BFS is the natural solution here, as for all 'shortest path' problems on unweighted graphs
    // don't ignore -ve, -ve might be the last element
    private static int minJumps(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new LinkedList<>());
            }
            map.get(arr[i]).add(i);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            // WARNING: don't do: int i = 0; i < q.size(); i++ size will change
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == n - 1) {
                    return steps;
                }

                // add neighbours (left and right)
                List<Integer> next = map.get(arr[cur]);
                next.add(cur-1);
                next.add(cur+1);

                for (int neigh: next) {
                    if (neigh >= 0 && neigh < n && !visited.contains(neigh)) {
                        visited.add(neigh);
                        q.offer(neigh);
                    }
                }
                next.clear(); // clear to avoid lookup map for same arr[i] later. this will reduce complexity to O(N). for case like 7,7,7,7,7,7,7
            }
            steps++;

        }
        return 0;
    }
}
