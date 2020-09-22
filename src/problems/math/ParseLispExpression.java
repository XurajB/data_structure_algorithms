package problems.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string expression representing a Lisp-like expression to return the integer value of.
 *
 * The syntax for these expressions is given as follows.
 *
 * An expression is either an integer, a let-expression, an add-expression, a mult-expression, or an assigned variable. Expressions always evaluate to a single integer.
 * (An integer could be positive or negative.)
 * A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let", then there are 1 or more pairs of alternating variables and expressions,
 * meaning that the first variable v1 is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2, and so on sequentially;
 * and then the value of this let-expression is the value of the expression expr.
 * An add-expression takes the form (add e1 e2) where add is always the string "add", there are always two expressions e1, e2, and this expression evaluates
 * to the addition of the evaluation of e1 and the evaluation of e2.
 * A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always two expressions e1, e2, and this expression evaluates to
 * the multiplication of the evaluation of e1 and the evaluation of e2.
 * For the purposes of this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits.
 * Additionally for your convenience, the names "add", "let", or "mult" are protected and will never be used as variable names.
 * Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation,
 * the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially.
 * It is guaranteed that every expression is legal. Please see the examples for more details on scope.
 * Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
 * Output: 14
 */
public class ParseLispExpression {
    public static void main(String[] args) {
        //System.out.println(evaluate("(let x 2 (mult x 5))"));
        System.out.println(evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
        // first example: start from left: check base case, parse..
        // first parse: let, x, 2, (mult x 5)
        // x = 2 (from base case)
        // second parse: mult, x, 5
        // return 2 * 5 = 10
    }

    private static int evaluate(String expression) {
        return calc(expression, new HashMap<>());
    }

    private static int calc(String expr, Map<String, Integer> parent) {
        // base case (will only have a variable or a number)
        if (expr.charAt(0) != '(') {
            if (Character.isDigit(expr.charAt(0)) || expr.charAt(0) == '-') {
                // number
                return Integer.parseInt(expr);
            } else {
                // variable
                return parent.get(expr);
            }
        }

        // create a deep copy of parent and add/update variables because the values might have changed
        Map<String, Integer> map = new HashMap<>(parent);

        // parse the expression
        // first remove outer bracket
        String s = expr.substring(1, expr.length() - 1);
        List<String> tokens = parse(s);

        // check what kind of operation
        if (s.charAt(0) == 'a') {
            return calc(tokens.get(1), map) + calc(tokens.get(2), map);
        } else if (s.charAt(0) == 'm') {
            return calc(tokens.get(1), map) * calc(tokens.get(2), map);
        } else {
            // let, there could be multiple lets
            for (int i = 1; i < tokens.size() - 1; i+= 2) {
                map.put(tokens.get(i), calc(tokens.get(i+1), map));
            }
            // remaining part of expression: (mult x 5)
            return calc(tokens.get(tokens.size() - 1), map);
        }
    }

    private static List<String> parse(String s) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                open--;
            }

            if (open == 0 && c == ' ') {
                ans.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }

        // last part
        if (sb.length() > 0) {
            ans.add(sb.toString());
        }

        return ans;
    }
}