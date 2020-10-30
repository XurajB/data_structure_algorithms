package problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 */
public class XofAKindInADeck {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,4,3,2,1};
        System.out.println(hasGroupsSizeX(nums));
    }

    // O(N)
    // think about case like: 1 1 1 1 2 2
    private static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> freq = new HashMap<>();
        int ans = 0;
        for (int i: deck) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        for (int i: freq.values()) {
            ans = gcd(i, ans);
        }
        return ans > 1; // gcd needs to be at least 2
    }

    // or gcf
    private static int gcd(int a, int b) {
        if (b > 0) {
            return gcd(b, a % b);
        }
        return a;
    }
}
