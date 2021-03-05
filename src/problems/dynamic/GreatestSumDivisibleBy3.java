package problems.dynamic;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 */
public class GreatestSumDivisibleBy3 {
    public static void main(String[] args) {
        System.out.println(maxSumDivThree(new int[] {3, 6, 5, 1, 8}));
    }

    private static int maxSumDivThree(int[] nums) {
        long leftOne = Integer.MAX_VALUE; // smallest number if sum%3 == 1. long to prevent overflow or use high int num like 20000
        long leftTwo = Integer.MAX_VALUE; // smallest number if sum%3 == 2

        // check the final sum % 3, if reminder is 1, then we need to remove the min number that caused it. if reminder is 2, same thing
        // keep the smallest two numbers that have n1%3==1 and n2%3==2
        int sum = 0;
        for (int num: nums) {
            sum += num;

            if (num % 3 == 1) {
                leftTwo = Math.min(leftTwo, leftOne + num); // two numbers with num%3 = 1 combined will equal to be num%3 = 2
                leftOne = Math.min(leftOne, num);
            } else if (num % 3 == 2) {
                leftOne = Math.min(leftOne, leftTwo + num); // two numbers with num%3 = 2 combined will equal to be num%3 = 1
                leftTwo = Math.min(leftTwo, num);
            }
        }

        // remove min to maximize the sum
        if (sum % 3 == 0) {
            return sum;
        }
        if (sum % 3 == 1) {
            return (int) (sum - leftOne);
        }
        return (int) (sum - leftTwo);
    }
}
