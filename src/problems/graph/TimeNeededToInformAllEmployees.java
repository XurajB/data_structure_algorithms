package problems.graph;

import java.util.*;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1.
 * Also it's guaranteed that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the employees of the company of an urgent piece of news.
 * He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes,
 * all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 */
public class TimeNeededToInformAllEmployees {

    public static void main(String[] args) {
        int[] manager = {1,2,3,4,5,6,-1};
        int[] informTime = {0,6,5,4,3,2,1};

        System.out.println(numOfMinutes(7, 6, manager, informTime));
    }

    // BFS, O(N), O(N)
    private static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                graph.get(manager[i]).add(i);
            }
        }

        // since it is a tree, we don't need visited

        int ans = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {headID, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int emp = cur[0];
            int time = cur[1];

            ans = Math.max(ans, cur[1]);

            for (int report: graph.get(emp)) {
                q.offer(new int[] {report, time + informTime[emp]});
            }
        }

        return ans;
    }

    ////////////////
    // DFS
    // O(N), O(N)
    private static int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                graph.get(manager[i]).add(i);
            }
        }

        return dfs(graph, informTime, headID);
    }

    private static int dfs(Map<Integer, Set<Integer>> graph, int[] informTime, int cur) {
        if (!graph.containsKey(cur)) {
            return 0;
        }
        int time = 0;
        for (int emp: graph.get(cur)) {
            time = Math.max(time, dfs(graph, informTime, emp));
        }

        return time + informTime[cur];
    }
}
