package problems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class NestedIterator2 implements Iterator<Integer> {
    private Deque<NestedInteger> stack;

    public NestedIterator2(List<NestedInteger> nestedList) {
        // this will put in the order we require, no need to reverse
        stack = new ArrayDeque<>(nestedList);
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        makeStackTopAnInteger();
        return !stack.isEmpty();
    }

    private void makeStackTopAnInteger() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> nestedList = stack.poll().getList();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
