package problems.string;

/**
 * There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 * The move sequence is represented by a string, and the character moves[i] represents its ith move. Valid moves are R (right), L (left), U (up), and D (down).
 * If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.
 */
public class RobotReturnToOrigin {
    public static void main(String[] args) {
        System.out.println(judgeCircle("UDLRLR"));
    }
    private static boolean judgeCircle(String moves) {
        int vertical = 0;
        int horizontal = 0;
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (c == 'U' || c == 'D') {
                vertical += c == 'U' ? 1 : -1;
            } else if (c == 'L' || c == 'R') {
                horizontal += c == 'L' ? 1 : -1;
            }
        }
        return (vertical == 0 && horizontal == 0);
    }
}
