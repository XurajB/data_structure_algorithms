package problems.binarysearch;

/**
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * The i-th package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 *
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/discuss/413536/How-to-figure-out-that-binary-search-would-be-applied-here
 */
public class CapacityToShipPackagesWithinDDays {

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int D = 5;
        System.out.println(shipWithinDays(weights, D));
    }

    // O(nlogn)
    private static int shipWithinDays(int[] weights, int D) {
        int left = 0; // max of weights - left boundary
        int right = 0; // total sum
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        // binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int days = 1;
            int cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    days++;
                    cur = 0;
                }
                cur += w;
            }
            if (days > D) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }

        return left;
    }
}
