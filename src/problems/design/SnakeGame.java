package problems.design;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 */
public class SnakeGame {
    int width;
    int height;

    int foodIndex;
    int[][] food;

    Deque<Integer> body; // update tail
    Set<Integer> set; // fast look-up for self colliding (eating own body)
    int score = 0;
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodIndex = 0;

        body = new LinkedList<>();
        set = new HashSet<>();

        set.add(0); // initially at 0,0
        body.offerLast(0);
    }

    // the idea is to add new head and remove tail
    // encode position = x * width + y
    public int move(String direction) {
        // game already over
        if (score == -1) {
            return -1;
        }

        // compute new head from old head
        int newX = body.peek() / width;
        int newY = body.peek() % width;

        switch (direction) {
            case "U": newX--;
                break;
            case "D": newX++;
                break;
            case "L": newY--;
                break;
            default: newY++;
        }

        int head = newX * width + newY;

        // case 1, out of boundary or eating body
        // you moved so remove last tail
        set.remove(body.peekLast());
        if (newX < 0 || newX >= height || newY < 0 || newY >= width || set.contains(head)) {
            score = -1;
            return score;
        }

        // add head for case2 and 3
        set.add(head);
        body.offerFirst(head);

        // case 2: eating food, keep tail and add head
        if (foodIndex < food.length && newX == food[foodIndex][0] && newY == food[foodIndex][1]) {
            set.add(body.peekLast()); // old tail does not change, so add it back to set (we removed for case 1)
            foodIndex++;
            score++;

            return score;
        }

        // case 3: normal move, remove tail, add head
        body.pollLast();

        return score;
    }
}
