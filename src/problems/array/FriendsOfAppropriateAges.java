package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 *
 * How many total friend requests are made?
 */
public class FriendsOfAppropriateAges {
    public static void main(String[] args) {
        System.out.println(numFriendRequests(new int[] {16,17,18}));
    }
    // O(N)
    private static int numFriendRequests(int[] ages) {
        Map<Integer, Integer> count = new HashMap<>();
        // there are 1-120 age range, but 20k people
        for (int age: ages) {
            count.put(age, count.getOrDefault(age, 0) + 1);
        }

        int request = 0;
        for (int a: count.keySet()) { // 120
            for (int b: count.keySet()) {
                if (request(a, b)) {
                    if (a==b) {
                        // if they have same age, reduce 1 as you can not friend yourself
                        request += count.get(a) * (count.get(b) - 1);
                    } else {
                        request += count.get(a) * count.get(b);
                    }
                }
            }
        }

        return request;
    }

    private static boolean request(int a, int b) {
        return !(b > a || b <= (0.5 * a + 7) || (b > 100 && a < 100));
    }
}
