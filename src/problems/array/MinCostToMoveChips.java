package problems.array;

/**
 * We have n chips, where the position of the ith chip is position[i].
 *
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 *
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 */
public class MinCostToMoveChips {
    public static void main(String[] args) {
        System.out.println(minCostToMoveChips(new int[] {2,2,2,3,3}));
    }

    private static int minCostToMoveChips(int[] chips) {
        // move all even chips to 0 with zero cost
        // move all odd chips to 1 with zero cost
        // now move final piles to either 0 or 1, whichever is min
        int even = 0;
        int odd = 0;
        for (int i: chips) {
            if (i%2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(even, odd);
    }
}
