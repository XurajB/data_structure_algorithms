package maths;

/**
 * Absolute max - absMax([3, 5, -11, 0]) = -11
 */

public class AbsoluteMax {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, -11, 0};
        System.out.println("Abs max: " + absMax(nums));
    }

    private static int absMax(int[] numbers) {
        int absMax = numbers[0];

        for (int i = 1, length = numbers.length; i < length; i++) {
            if (Math.abs(numbers[i]) > Math.abs(absMax)) {
                absMax = numbers[i];
            }
        }

        return absMax;
    }
}
