package problems.map;

import java.util.Stack;
import java.util.TreeMap;

/**
 * Given a chemical formula (given as a string), return the count of each atom.
 * An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 * 1 or more digits representing the count of that element may follow if the count is greater than 1.
 * If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
 * Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
 * A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
 * Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order),
 * followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 */
public class NumberOfAtoms {
    public static void main(String[] args) {
        System.out.println(countOfAtoms("Mg(OH)2"));
    }

    // O(nlogn)
    private static String countOfAtoms(String formula) {

        TreeMap<String, Integer> map = new TreeMap<>(); // so we get sorted atoms at the end
        Stack<TreeMap<String, Integer>> stack = new Stack<>();

        int i = 0;
        int n = formula.length();

        while (i < n) {

            if (formula.charAt(i) == '(') {
                stack.push(map);
                map = new TreeMap<>();
                i++;
            } else if (formula.charAt(i) == ')') {
                int count = 0;
                int j = i + 1;
                while (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    count = count * 10 + formula.charAt(j) - '0';
                    j++;
                }

                if (!stack.isEmpty()) {
                    TreeMap<String, Integer> temp = map;
                    map = stack.pop();

                    for (String atom: temp.keySet()) {
                        map.put(atom, temp.get(atom) * count + map.getOrDefault(atom, 0));
                    }
                }

                i = j;
            } else { // it has to be starting of another atom
                int j = i + 1;
                // atom
                while (j < formula.length() && Character.isLowerCase(formula.charAt(j))) {
                    j++;
                }

                String atom = formula.substring(i, j);

                // count
                int count = 0;
                while (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    count = count * 10 + formula.charAt(j) - '0';
                    j++;
                }

                count = count == 0 ? 1 : count;
                map.put(atom, map.getOrDefault(atom, 0) + count);

                i = j;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (String atom: map.keySet()) {
            sb.append(atom);
            sb.append(map.get(atom) == 1 ? "" : map.get(atom));
        }

        return sb.toString();
    }
}
