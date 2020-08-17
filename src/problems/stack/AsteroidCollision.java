package problems.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {
    public static void main(String[] args) {
        int[] asteroids = {5,10,-5};
        System.out.println(Arrays.toString(asteroidCollision(asteroids)));
    }

    private static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int asteroid: asteroids) {
            // if > 0 push to stack
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0
                        && stack.peek() < Math.abs(asteroid)) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                } else if (stack.peek() + asteroid == 0) { // check if they are equal and opp
                    stack.pop();
                }
            }
        }

        int[] ans = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[i--] = stack.pop();
        }

        return ans;
    }
}