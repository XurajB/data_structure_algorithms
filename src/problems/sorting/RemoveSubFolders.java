package problems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 */
public class RemoveSubFolders {
    public static void main(String[] args) {
        System.out.println(removeSubfolders(new String[] {"/a","/a/b","/c/d","/c/d/e","/c/f"}));
    }

    // O(nlogn)
    private static List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();

        Arrays.sort(folder);

        String last = folder[0];
        ans.add(last);

        for (int i = 1; i < folder.length; i++) {
            String cur = folder[i];

            // since list is sorted, only check with last
            // add / to last for cases like /a/b/c, /a/b/ca
            // don't use contains coz: /a, /b/a
            if (!cur.startsWith(last+"/")) {
                ans.add(cur);
                last = cur;
            }
        }

        return ans;
    }
}
