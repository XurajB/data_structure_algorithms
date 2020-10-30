package problems.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 */
public class AlphabetBoardPath {
    public static void main(String[] args) {
        System.out.println(alphabetBoardPath("leet"));
    }

    private static String alphabetBoardPath2(String target) {
        StringBuilder sb = new StringBuilder();
        int lastRow = 0;
        int lastCol = 0;

        for (int i = 0; i < target.length(); i++) {
            int curRow = (target.charAt(i) - 'a') / 5;
            int curCol = (target.charAt(i) - 'a') % 5;

            // order is important, LU should come before RD because of z being the only char in last line
            // other char to z: L then D
            // z to other char: U then R
            // only only loop: LDUR or URLD
            while (lastRow > curRow) {
                sb.append("U");
                lastRow--;
            }

            while (lastCol < curCol) {
                sb.append("R");
                lastCol++;
            }

            while (lastCol > curCol) {
                sb.append("L");
                lastCol--;
            }

            while (lastRow < curRow) {
                sb.append("D");
                lastRow++;
            }

            sb.append("!");
        }

        return sb.toString();
    }

    ////////////////////////////
    // BFS
    private static String alphabetBoardPath(String target) {

        String[] board = {"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};

        StringBuilder ans = new StringBuilder();

        int x = 0;
        int y = 0;
        for (int i = 0; i < target.length(); i++) {
            Node node = new Node(x, y);
            Result result = bfs(board, new Node(x, y), target.charAt(i));
            ans.append(result.ans).append("!");
            x = result.i;
            y = result.j;
        }

        return ans.toString();

    }

    private static Result bfs(String[] board, Node start, char c) {

        int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
        char[] chars = {'D', 'R', 'L', 'U'};
        Queue<Node> q = new LinkedList<>();
        q.offer(start);

        boolean[][] visited = new boolean[6][5];
        visited[start.i][start.j] = true;

        while (!q.isEmpty()) {

            int size = q.size();
            for (int k = 0; k < size; k++) {

                Node cur = q.poll();

                int i = cur.i;
                int j = cur.j;

                if (board[i].charAt(j) == c) {
                    return new Result(i, j, cur.path.toString());
                }

                for (int n = 0; n < 4; n++) {
                    int[] dir = dirs[n];
                    int newi = i + dir[0];
                    int newj = j + dir[1];

                    if (newi >= 0 && newj >=0 && newi < board.length && newj < board[newi].length() && !visited[newi][newj]) {
                        Node newnode = new Node(newi, newj);
                        newnode.path = new StringBuilder(cur.path);
                        newnode.path.append(chars[n]);

                        visited[newi][newj] = true;
                        q.offer(newnode);
                    }
                }
            }

        }

        return new Result(-1, -1, "No");
    }

    static class Node {
        int i;
        int j;
        StringBuilder path = new StringBuilder();
        Node (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Result {
        int i;
        int j;
        String ans;
        Result (int i, int j, String ans) {
            this.i = i;
            this.j = j;
            this.ans = ans;
        }
    }
}
