package problems.binarysearch;

/**
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.
 * If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
 *
 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 *
 * Return the minimum integer K such that she can eat all the bananas within H hours.
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[] {3,6,7,11}, 4));
    }

    // if we take 4 as eating rate: then she takes: 1 + 2 + 2 + 2 hours to eat all
    private static int minEatingSpeed(int[] piles, int H) {
        int max = 0;
        for (int pile: piles) {
            max = Math.max(max, pile);
        }
        int lo = 1;
        int high = max;
        while (lo <= high) {
            int mid = lo + (high - lo) / 2;
            int hour = 0;
            for (int pile: piles) {
                hour += ((pile - 1) / mid) + 1; // pile == mid, pile/mid = 0, to make  +1 valid
            }
            if (hour == H) {
                return mid;
            } else if (hour > H) {
                lo = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return lo;
    }
}
