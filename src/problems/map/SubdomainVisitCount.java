package problems.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A website domain like "discuss.leetcode.com" consists of various subdomains.
 * At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com".
 * When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
 *
 * Now, call a "count-paired domain" to be a count (representing the number of visits this domain received),
 * followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
 *
 * We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains,
 * (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
 */
public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String domain: cpdomains) {
            String[] cp = domain.split(" ");
            int count = Integer.parseInt(cp[0]);
            String dom = cp[1];
            while (dom.length() != 0) {
                if (dom.contains(".")) {
                    map.put(dom, map.getOrDefault(dom, 0)+count);
                    dom = dom.substring(dom.indexOf(".")+1);
                } else {
                    map.put(dom, map.getOrDefault(dom, 0)+count);
                    dom = "";
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (String dom: map.keySet()) {
            ans.add(map.get(dom) + " " + dom);
        }
        return ans;
    }
}
