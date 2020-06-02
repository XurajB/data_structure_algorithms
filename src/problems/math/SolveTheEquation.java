package problems.math;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * https://leetcode.com/problems/solve-the-equation/
 */
public class SolveTheEquation {

    public static void main(String[] args) {
        SolveTheEquation equation = new SolveTheEquation();
        System.out.println(equation.solveEquation("2x+3x-6x=x+2"));
    }

    // O(N)
    public String solveEquation(String equation) {

        int[] left = solve(equation.split("=")[0]);
        int[] right = solve(equation.split("=")[1]);

        int x = left[0] - right[0];
        int val = right[1] - left[1];

        if (x == 0 && val == 0) {
            return "Infinite solutions";
        }
        if (x == 0) {
            return "No solution";
        }
        return "x=" + val/x;
    }

    private int[] solve(String exp) {
        int[] res = new int[2]; // 0 - sum of x, 1 - sum of nums
        //String[] splits = exp.split("(?=[+-])")
        String[] splits = exp.replace("+", "#+").replace("-", "#-").split("#");

        for (String split: splits) {
            if (split.equals("")) {
                continue;
            }
            if (split.equals("x") || split.equals("+x")) {
                res[0]++;
            } else if (split.equals("-x")) {
                res[0]--;
            } else if (split.contains("x")) {
                res[0] += Integer.parseInt(split.substring(0, split.indexOf("x")));
            } else {
                res[1] += Integer.parseInt(split);
            }

        }
        return res;
    }
}
