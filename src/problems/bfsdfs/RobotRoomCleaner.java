package problems.bfsdfs;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {
     interface Robot {
         // Returns true if the cell in front is open and robot moves into the cell.
         // Returns false if the cell in front is blocked and robot stays in the current cell.
         public boolean move();
         // Robot will stay in the same cell after calling turnLeft/turnRight.
         // Each turn will be 90 degrees.
         public void turnLeft();
         public void turnRight();
         // Clean the current cell
         public void clean();
     }

     // we are going clock wise (always taking right turn), up, right, down, left. can be any order but always right
     int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
     Set<Pair<Integer, Integer>> visited = new HashSet<>();
     Robot robot;

     // O(4 * E), E = empty cells
     public void cleanRoom(Robot robot) {
         this.robot = robot;
         backtrack(0, 0, 0); // start with 0, 0 (does not matter where the robot is placed, it is relative)
     }

     // this algorithm is based on maze solving algorithm called right-hand rule.
     // go forward, cleaning and marking all the cells on the way and turn right at obstacle
     private void backtrack(int row, int col, int dir) {
         visited.add(new Pair<>(row, col));
         robot.clean();

         // going clock wise
         for (int i = 0; i < 4; i++) {
             // (dir+i) to make sure we continue clock wise, other wise we will be lost of we just follow for(int[] dir:dirs)
             int newDirection = (dir + i) % 4; // dir + i, can be more than 4
             int newR = row + dirs[newDirection][0];
             int newC = col + dirs[newDirection][1];
             if (!visited.contains(new Pair<>(newR, newC)) && robot.move()) {
                 backtrack(newR, newC, newDirection);
                 // backtrack
                 goBack();
             }
             // if cannot move then always turn right
             robot.turnRight();
         }
     }

     private void goBack() {
         // we are taking one step back
         robot.turnRight();
         robot.turnRight();
         robot.move();
         robot.turnRight();
         robot.turnRight();
         // or take two turnLeft and two turnRight
     }
}
