package problems;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        pq.add(0);
        pq.add(5);
        pq.add(15);
        pq.add(7);
        pq.add(3);

        for (int i = 0; i < 5; i++) {
            System.out.println(pq.poll());
        }

    }


}
