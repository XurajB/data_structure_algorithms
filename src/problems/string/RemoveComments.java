package problems.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove comments from code. Line comment and block comment
 * https://leetcode.com/problems/remove-comments/
 */
public class RemoveComments {
    public static void main(String[] args) {
        String[] source = {
                "/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"
        };
        System.out.println(removeComments(source));
    }

    private static List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean hasBlockComment = false;
        for (String s : source) {
            for (int i = 0; i < s.length(); i++) {
                if (hasBlockComment) {
                    if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        hasBlockComment = false;
                        i++;        //skip '/' on next iteration of i
                    }
                }
                else {
                    if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        break;      //ignore remaining characters on line s
                    }
                    else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                        hasBlockComment = true;
                        i++;           //skip '*' on next iteration of i
                    }
                    else    sb.append(s.charAt(i));     //not a comment
                }
            }
            if (!hasBlockComment && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();   //reset for next line of source code
            }
        }
        return res;
    }
}
