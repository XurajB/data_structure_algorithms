package problems.graph;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 */
public class CourseSchedule {
    // m+n
    // topo
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pre: prerequisites) {
            int in = pre[1];
            int out = pre[0];
            if (!map.containsKey(in)) {
                map.put(in, new ArrayList<>());
            }
            map.get(in).add(out);
            indegree[out]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) { // note n, not map.keySet
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            count++;
            if (map.containsKey(cur)) {
                for (int neigh: map.get(cur)) {
                    indegree[neigh]--;
                    if (indegree[neigh] == 0) {
                        q.offer(neigh);
                    }
                }
            }
        }
        return count == numCourses;
    }
}
