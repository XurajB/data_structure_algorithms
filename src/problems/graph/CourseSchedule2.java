package problems.graph;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * https://leetcode.com/problems/course-schedule-ii/
 */
// topological sort
public class CourseSchedule2 {
    // O(N), O(N)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }

        int[] order = new int[numCourses]; //answer
        int[] indegree = new int[numCourses];

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] preq: prerequisites) {
            int source = preq[1];
            int destination = preq[0];
            indegree[destination]++;
            // create a adjacency list
            if (!graph.containsKey(source)) {
                graph.put(source, new ArrayList<>());
            }
            graph.get(source).add(destination);
        }

        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                order[index++] = i;
            }
        }

        while (!queue.isEmpty()) {
            int preq = queue.poll();

            if (graph.containsKey(preq)) {
                for (int i : graph.get(preq)) {
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        order[index++] = i;
                        queue.offer(i);
                    }
                }
            }
        }

        if (index == numCourses) {
            return order;
        }
        return new int[0];
    }
}
