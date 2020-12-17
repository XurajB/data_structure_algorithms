package problems.twopointers;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    // O(n), O(1)
    private static int trap(int[] height) {

        int leftMax = 0;
        int rightMax = 0;

        int i = 0;
        int j = height.length - 1;

        int total = 0;

        while (i < j) {
            if (height[i] < height[j]) {
                leftMax = Math.max(leftMax, height[i]);
                total += leftMax - height[i];
                i++;
            } else {
                rightMax = Math.max(rightMax, height[j]);
                total += rightMax - height[j];
                j--;
            }
        }
        return total;
    }
}
