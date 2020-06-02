package problems.design;

import java.util.*;

/**
 * Design an in-memory file system
 * https://leetcode.com/problems/design-in-memory-file-system/
 */
public class InMemoryFileSystem {
    private File root;
    static class File {
        boolean isFile = false;
        Map<String, File> directories = new TreeMap<>(); // treemap keep sorted list of keys
        StringBuilder content = new StringBuilder();
    }

    public InMemoryFileSystem() {
        root = new File();
    }

    // O(m + n + logk - treemap sorting), m is path length, n is depth of last directory level, k is number of entries (files + dirs)
    public List<String> ls(String path) {
        List<String> ans = new ArrayList<>();

        File node = root;
        String[] dirs = path.split("/");
        String fileName = "";
        for (String dir: dirs) {
            if (dir.length() == 0) { // splits array will include empty strings
                continue;
            }
            if (!node.directories.containsKey(dir)) {
                return ans;
            }
            node = node.directories.get(dir);
            fileName = dir;
        }

        if (node.isFile) {
            ans.add(fileName);
        } else {
            for (String s: node.directories.keySet()) {
                ans.add(s);
            }
        }
        return ans;
    }

    // O(m+n)
    public void mkdir(String path) {
        navigateTo(path);
    }

    // O(m+n)
    public void addContentToFile(String filePath, String content) {
        File file = navigateTo(filePath);
        file.isFile = true;
        file.content.append(content);
    }

    // O(m+n)
    public String readContentFromFile(String filePath) {
        File file = navigateTo(filePath);
        return file.content.toString();
    }

    private File navigateTo(String path){
        File node = root;
        String[] dirs = path.split("/");
        for (String dir: dirs) {
            if (dir.length() == 0) {
                continue;
            }
            if (!node.directories.containsKey(dir)) {
                node.directories.put(dir, new File());
            }
            node = node.directories.get(dir);
        }
        return node;
    }
}
