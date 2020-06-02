package problems.graph.unionfind;

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

        AccountsMerge merge = new AccountsMerge();
        List<List<String>> answer = merge.accountsMerge(accounts);
        for (List<String> account: answer) {
            System.out.println(account);
        }
    }

    // O(ALogA) - A = length of accounts
    private List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parents = new HashMap<>(); // email, email (parent)
        Map<String, String> owners = new HashMap<>(); // email, name
        Map<String, TreeSet<String>> unions = new HashMap<>(); // treeset so we get sorted set

        // set parent of each emails to itself
        // set owner of each email
        for (List<String> account: accounts) {
            for (int i = 1; i < account.size(); i++) {
                parents.put(account.get(i), account.get(i));
                owners.put(account.get(i), account.get(0));
            }
        }

        // go through the accounts and find parents
        // find parent of the first email
        for (List<String> account: accounts) {
            String parent = find(account.get(1), parents);
            for (int i = 2; i < account.size(); i++) {
                parents.put(find(account.get(i), parents), parent);
            }
        }

        // using parents as keys, set rest of the emails in the union map
        for (List<String> account: accounts) {
            String parent = find(account.get(1), parents);
            if (!unions.containsKey(parent)) {
                unions.put(parent, new TreeSet<>());
            }
            for (int i = 1; i < account.size(); i++) {
                unions.get(parent).add(account.get(i));
            }
        }

        // construct answers based on unions keys
        List<List<String>> result = new ArrayList<>();
        for (String parent: unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(parent));
            emails.add(0, owners.get(parent));
            result.add(emails);
        }

        return result;
    }

    private String find(String email, Map<String, String> parents) {
        if (parents.get(email).equals(email)) {
            return email;
        }
        return find(parents.get(email), parents);
    }
}
