package problems.bfsdfs;

import java.util.*;

/**
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 */
public class SlidingPuzzle {
    public static void main(String[] args) {
        int[][] board = {{1,2,3},{4,0,5}};
        System.out.println(slidingPuzzle(board));
    }

    private static int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        String target = "123450";
        // build the start string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(sb.toString());
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());

        // we calculate possible directions based on board target
        // all positions that 0 can be swapped to, there are 6 indexes that 0 can be and 6 different directions
        // example of 0 being at index 0 (think of the target being a single string)
        // 0 1 2
        // 4 5 3
        // If 0 is at idx 0, then it can be swapped to idx 1 or 3.
        // If 0 is at idx 1, then it can be swapped to idx 0 or 2 or 4.
        // If 0 is at idx 2, etc.
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5},
                        {0, 4}, {1, 3, 5}, {2, 4}};
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) {
                    return level;
                }

                int zeroIndex = current.indexOf('0');
                for (int dir: dirs[zeroIndex]) {
                    String next = swap(current, zeroIndex, dir);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            level++; // we added in initial board in queue and if it is matched with target in first shot, ans is 0
        }

        return -1;
    }

    private static String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
