package problems.design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * mplement FreqStack, a class which simulates the operation of a stack-like data structure.
 *
 * FreqStack has two functions:
 *
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 */
public class FrequencyStack {
    int pushCount;
    PriorityQueue<Node> pq;
    Map<Integer, Integer> map;
    public FrequencyStack() {
        pushCount = 0;
        pq = new PriorityQueue<>(new NodeComparator());
        map = new HashMap<>();
    }

    // O(logn)
    public void push(int x) {
        if (!map.containsKey(x)){
            map.put(x, 0);
        }
        map.put(x, map.get(x) + 1);
        Node n = new Node(x, map.get(x), pushCount++);
        pq.offer(n);
    }

    // O(1)
    public int pop() {
        map.put(pq.peek().value, pq.peek().freq - 1);
        return pq.poll().value;
    }

    static class Node {
        public int value;
        int freq;
        int pushIndex;
        public Node(int v, int f, int p){
            this.value = v;
            this.freq = f;
            this.pushIndex = p;
        }
    }

    class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b){
            if (a.freq != b.freq) return b.freq -a.freq;
            return b.pushIndex - a.pushIndex;

        }
    }
}
