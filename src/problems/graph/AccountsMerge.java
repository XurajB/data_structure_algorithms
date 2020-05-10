package problems.graph;

import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * https://leetcode.com/problems/accounts-merge/
 */
public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();

        List<String> name1 = new ArrayList<>();
        name1.add("John");
        name1.add("e1");
        name1.add("e2");
        accounts.add(name1);

        List<String> name2 = new ArrayList<>();
        name2.add("John");
        name1.add("e3");
        name2.add("e1");
        accounts.add(name2);

        List<String> name3 = new ArrayList<>();
        name3.add("Mary");
        name3.add("e4");
        accounts.add(name3);

        List<String> name4 = new ArrayList<>();
        name4.add("John");
        name4.add("e5");
        accounts.add(name4);

        List<List<String>> answer = accountsMerge(accounts);
        for (List<String> account: answer) {
            System.out.println(account);
        }
    }

    private static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Set<String>> graph = new HashMap<>();

        //  construct the graph
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if (!graph.containsKey(account.get(i))) {
                    graph.put(account.get(i), new HashSet<>());
                }
                graph.get(account.get(i)).add(account.get(1));
                graph.get(account.get(1)).add(account.get(i));
            }
        }

        // iterate the non seen accounts and merge them
        Set<String> visited = new HashSet<>();
        for (List<String> account : accounts) {
            if (!visited.contains(account.get(1))) {
                List<String> list = new ArrayList<>();
                bfs(graph, account.get(1), visited, list);
                Collections.sort(list);
                // add the name of the account to the list
                list.add(0, account.get(0));
                res.add(list);
            }
        }
        return res;
    }

    private static void bfs(Map<String, Set<String>> graph, String s, Set<String> seen, List<String> list) {
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        seen.add(s);
        while (!q.isEmpty()) {
            String cur = q.poll();
            list.add(cur);
            for (String neig : graph.get(cur)) {
                if (!seen.contains(neig)) {
                    q.offer(neig);
                    seen.add(neig);
                }
            }
        }
    }
}
