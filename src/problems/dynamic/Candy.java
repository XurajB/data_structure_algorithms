package problems.dynamic;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
public class Candy {
    public static void main(String[] args) {
        int[] ratings = {1,0,5};
        System.out.println(candy(ratings));
    }

    // O(N)
    private static int candy(int[] ratings) {
        int n = ratings.length;

        int[] candies = new int[n];
        // give everyone 1 candy
        Arrays.fill(candies, 1);

        // make sure higher rating gets more candy than its left
        for (int i = 1; i < candies.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i-1] +1 ;
            }
        }

        // make sure higher rating gets more candy than its right
        for (int i = candies.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1] + 1); // candy[i] could already be higher
            }
        }

        int ans = 0;
        for (int candy: candies) {
            ans += candy;
        }

        return ans;
    }
}
