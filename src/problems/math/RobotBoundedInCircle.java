package problems.math;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
public class RobotBoundedInCircle {
    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGG"));
    }

    // we can run this 4 times and see it the robot comes back to 0
    // or 1 time: then if it return back to 0 or not face north
    private static boolean isRobotBounded(String instructions) {

        int dir = 0; // starting N
        int x = 0;
        int y = 0;

        // N, E, S, W
        int[][] dirs = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);

            if (c == 'L') {
                dir = (dir + 3) % 4;
            } else if (c == 'R') {
                dir = (dir + 1) % 4;
            } else {
                x += dirs[dir][0];
                y += dirs[dir][1];
            }
        }

        if (x == 0 && y == 0) {
            return true;
        }
        // either robot comes back to 0 or not facing north
        return dir != 0;
    }
}
