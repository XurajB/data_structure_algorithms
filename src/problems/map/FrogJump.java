package problems.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
 * https://leetcode.com/problems/frog-jump/
 */
public class FrogJump {
    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones));
    }

    // O(N^2), O(N)
    private static boolean canCross(int[] stones) {
        if (stones.length == 0) {
            return true;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>(); // stone, if the frog stands in this stone how many steps can it jump
        map.put(0, new HashSet<>());
        map.get(0).add(1); // first step is always 1

        for (int i = 1; i < stones.length; i++) { // start with 1
            map.put(stones[i], new HashSet<>());
        }

        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i];
            for (int step: map.get(stone)) {
                int reach = stone + step;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                if (map.containsKey(reach)) {
                    map.get(reach).add(step);
                    if (step - 1 > 0) {
                        map.get(reach).add(step-1);
                    }
                    map.get(reach).add(step+1);
                }
            }
        }
        return false;
    }
}

/**
 *   0 -> 1
 *   1 -> 1,2
 *   3 -> 1,2,3
 *   5 -> 1,2,3
 *   6 -> 1,2,3,4
 *   8 -> 1,2,3,4
 *   9 -> 3,4,5
 */
