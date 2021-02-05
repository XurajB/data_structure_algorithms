package problems.stack;

import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 *
 */
public class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
    }

    public static String simplifyPath(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String v : strs) {
            if(v.length() == 0 || v.equals(".")){
                continue;
            }
            if(v.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(v);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String v : stack){ //// NOTE: this will give us from the bottom LIFO. unlike stack.pop() that will give us the FIFO
            sb.append("/").append(v);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
