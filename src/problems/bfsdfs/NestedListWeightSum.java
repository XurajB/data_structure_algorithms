package problems.bfsdfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 */
public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger ni: nestedList) {
            sum += depthSum(ni, 1);
        }
        return sum;
    }

    private int depthSum(NestedInteger ni, int level) {
        if (ni.isInteger()) {
            return level * ni.getInteger();
        }
        int sum = 0;
        for (NestedInteger integer: ni.getList()) {
            sum += depthSum(integer, level + 1);
        }
        return sum;
    }

    //// shorter dfs, O(n)
    private int depthSum2(List<NestedInteger> ni, int level) {
        int sum = 0;
        for (NestedInteger integer: ni) {
            if (integer.isInteger()) {
                return level * integer.getInteger();
            } else {
                sum += depthSum2(integer.getList(), level + 1);
            }
        }
        return sum;
    }

    // bfs
    public int depthSum3(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger n : nestedList) {
            q.add(n);
        }
        int deep = 1;
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                NestedInteger temp = q.poll();
                if (temp.isInteger()) {
                    ans += deep * temp.getInteger();
                } else {
                    for (NestedInteger n : temp.getList()) {
                        q.add(n);
                    }
                }
            }
            deep++;
        }
        return ans;
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);
        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
