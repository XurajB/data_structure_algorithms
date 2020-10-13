package problems.bfsdfs;

import java.util.*;

/**
 * You are given a data structure of employee information, which includes the employee's unique id, their importance value and their direct subordinates' id.
 *
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]],
 * and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 *
 * Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all their subordinates.
 */
public class EmployeeImportance {
    // bfs
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee emp: employees) {
            map.put(emp.id, emp);
        }
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        while (!q.isEmpty()) {
            int cur = q.poll();
            Employee emp = map.get(cur);
            ans += emp.importance;
            for (Integer sub: emp.subordinates) {
                q.offer(sub);
            }
        }
        return ans;
    }

    // dfs
    public int getImportance2(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee emp: employees) {
            map.put(emp.id, emp);
        }
        return dfs(map, id);
    }

    private int dfs(Map<Integer, Employee> map, int id) {
        int ans = map.get(id).importance;
        for (int sub: map.get(id).subordinates) {
            ans += dfs(map, sub);
        }
        return ans;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };
}
