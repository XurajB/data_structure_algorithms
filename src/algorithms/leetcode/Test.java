package algorithms.leetcode;

public class Test {
    public static void main(String[] args) {
        String a = "";

        if (a.length() == 0) {
            return;
        }

        int position = 0;
        boolean isNegative = false;
        while (a.charAt(position) == ' ') {
            position++;

            if (position == a.length()) {
                return;
            }
        }

        if (a.charAt(position) == '-' || a.charAt(position) == '+') {
            isNegative = a.charAt(position) == '-';
            position++;
        }

        if (position == a.length()) {
            return;
        }

        int number = 0;
        while (a.charAt(position) >= '0' && a.charAt(position) <= '9') {
            number = number * 10 + a.charAt(position) - '0';
            position++;
            if (position == a.length()) {
                break;
            }
        }

        System.out.println(isNegative ? number * -1 : number);
    }


}
