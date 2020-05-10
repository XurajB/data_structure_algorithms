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
        int[] res = evaluateExpression(equation.split("=")[0]);
        int[] res2 = evaluateExpression(equation.split("=")[1]);
        res[0] -= res2[0];
        res[1] = res2[1] - res[1];
        if (res[0] == 0 && res[1] == 0) return "Infinite solutions";
        if (res[0] == 0) return "No solution";
        return "x=" + res[1]/res[0];
    }

    public int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])");
        int[] res =  new int[2];
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0] += 1;
            else if (token.equals("-x")) res[0] -= 1;
            else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else res[1] += Integer.parseInt(token);
        }
        return res;
    }

    public String solveEquation2(String equation) {

        String[] parts = equation.split("=");
        Pair left = solve(parts[0]);
        Pair right = solve(parts[1]);

        if (left.xcount == right.xcount && left.total == right.total) {
            return "Infinite solutions";
        }

        if (left.xcount == right.xcount) {
            return "No solution";
        }

        int x  = left.xcount - right.xcount;
        int total = right.total - left.total;

        total = total/x;
        return "x=" + total;
    }

    private Pair solve(String part) {

        int xCount = 0;
        int total = 0;
        int lastSign = 1;

        int lastnum = 0;
        for (int i = 0; i < part.length(); i++) {
            char c = part.charAt(i);

            if (Character.isDigit(c)) {
                lastnum = lastnum * 10 + c - '0';
            } else {
                if (c =='x') {
                    if (lastnum == 0) {
                        lastnum = 1;
                    }
                    xCount += lastnum * lastnum;
                } else {
                    total += total * lastnum;
                    lastSign = c == '+' ? 1 : -1;
                }
                lastnum = 0;
            }
        }
        // remaining part
        if (part.charAt(part.length() - 1) =='x') {
            xCount += lastnum * lastSign;
        } else {
            total += total * lastSign;
        }

        return new Pair(xCount, total);
    }

    static class Pair {
        int xcount = 0;
        int total = 0;
        Pair(int count, int total) {
            this.xcount = count;
            this.total = total;
        }
    }
}
