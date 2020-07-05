package problems.math;

import java.util.PriorityQueue;

public class UglyNumber2 {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(100));
    }

    // O(n)
    // the idea is to precompute numbers instead of checking each number
    // for 100th number is 1536, so instead of checking 1436 numbers we precompute 100 numbers
    private static int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1l);
        for (int i = 1; i < n; i++) {
            long tmp = pq.poll();
            while (!pq.isEmpty() && pq.peek() == tmp) {
                pq.poll(); // avoid dupes, we can also keep a seen set
            }
            pq.add(tmp * 2);
            pq.add(tmp * 3);
            pq.add(tmp * 5);
        }
        return pq.poll().intValue();
    }
}
