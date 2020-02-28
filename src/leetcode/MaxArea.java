package leetcode;

/**
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 */
public class MaxArea {

    /**
     * Max area to hold water
     */

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    // O(n), space O(1)
    private static int maxArea(int[] height) {
        // we start pointers at start and end
        // and move pointer of the lower height side and keep tracking maxArea
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
