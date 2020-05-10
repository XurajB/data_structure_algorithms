package problems.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 */
public class TheMaze {

    public static void main(String[] args) {
        int[][] path = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };
        System.out.println(hasPath(path, new int[] {0,4}, new int[] {4,4}));
    }

    private static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return false;
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // check if it is the destination
            if (current[0] == destination[0] && current[1] == destination[1]) {
                return true;
            }

            for (int[] dir: dirs) {
                int x = current[0] + dir[0];
                int y = current[1] + dir[1];

                // roll the ball until it hits a wall
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }

                // we hit the wall + 1 step over so step back
                x -= dir[0];
                y -= dir[1];
                if (!visited[x][y]) {
                    queue.offer(new int[] {x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }
}
