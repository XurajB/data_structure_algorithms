package problems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string input representing the file system in the explained format, return the length of the longest absolute path to a file in the abstracted file system.
 * If there is no file in the system, return 0.
 */
public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    private static int lengthOfLongestPath(String input) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>(); // levels, length
        map.put(0, 0); // 0 level, 0 length

        String[] arr = input.split("\n");
        for (String str: arr) {
            int numOfTabs = str.lastIndexOf("\t") + 1; // no \t return -1
            int levels = numOfTabs + 1;

            int len = str.length() - numOfTabs; // NOTE: \t length = 1
            if (str.contains(".")) {
                max = Math.max(max, map.get(levels - 1) + len); // compare with last level
            } else {
                map.put(levels, map.get(levels - 1) + len + 1); // add to last level + /. for first level we have 0,0 initialized
            }
        }
        return max;
    }

    private static int lengthOfLongestPath2(String input) {
        Deque<Integer> stack = new ArrayDeque<>(); // length of current path
        String[] arr = input.split("\n");
        int max = 0;

        stack.push(0); // for null length
        for (String str: arr) {
            // numOfTabs is number of \t
            // if no \t, then s.lastIndexOf returns -1
            // first parent dir have numOfTabs = 0;
            int numOfTabs = str.lastIndexOf("\t") + 1;
            // level is numOfTabs + 1, first level 1 (tabs = 0)
            // dir\n\tdir1\n\tdir2\n\t\tfile.txt, dir = level 1, dir1 dir2 = level 2, file.txt = level 3
            int levels = numOfTabs + 1;

            // if there are several subdirectories at the same level, remove them and calculate path length with cur directory or file we are at
            while (levels < stack.size()) {
                stack.pop();
            }
            int curLen = stack.peek() + str.length() - numOfTabs + 1;
            stack.push(curLen);

            if (str.contains(".")) {
                max = Math.max(max, curLen - 1); // remove last /
            }
        }
        return max;
    }
}
