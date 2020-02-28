package interviewbit;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Input 1:
 *     A =   ["2", "1", "+", "3", "*"]
 * Output 1:
 *     9
 * Explaination 1:
 *     starting from backside:
 *     *: ( )*( )
 *     3: ()*(3)
 *     +: ( () + () )*(3)
 *     1: ( () + (1) )*(3)
 *     2: ( (2) + (1) )*(3)
 *     ((2)+(1))*(3) = 9
 *
 * https://www.interviewbit.com/problems/evaluate-expression/
 */
public class EvaluateExpression {

    public static void main(String[] args) {
        String[] a = {"2", "1", "+", "3", "*"};
        String[] b = {"4", "13", "5", "/", "+"};

        System.out.println(evalRPN(a));
        System.out.println(evalRPN(b));
    }

    private static int evalRPN(String[] exp) {

        if (exp == null) {
            return 0;
        } else if (exp.length == 1) {
            return Integer.parseInt(exp[0]);
        }

        Stack<Integer> s = new Stack<>();
        int a1 = 0, a2 = 0;
        for (String str: exp) {
            if (str.equals("/") || str.equals("*") || str.equals("-") || str.equals("+")) {
                a1 = s.pop();
                a2 = s.pop();
                switch (str) {
                    case ("/"):
                        s.push(a2 / a1);
                        break;
                    case ("*"):
                        s.push(a2 * a1);
                        break;
                    case ("-"):
                        s.push(a2 - a1);
                        break;
                    case ("+"):
                        s.push(a2 + a1);
                        break;
                }
            } else {
                s.push(Integer.parseInt(str));
            }
        }
        return s.pop();
    }
}
