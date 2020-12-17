package problems.sorting;

import java.util.*;

/**
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.
 * https://leetcode.com/problems/analyze-user-website-visit-pattern/
 */
public class AnalyzeUserWebsiteVisitPattern {
    public static void main(String[] args) {
        String[] username = new String[] {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = new int[] {1,2,3,4,5,6,7,8,9,10};
        String[] website = new String[] {"home","about","career","home","cart","maps","home","home","about","career"};
        System.out.println(mostVisitedPattern(username, timestamp, website));
    }

    // collect user, (web, time) pair in a map
    // go through each user (sort the values by time)
    // traverse n^3 to get 3 sequence, and count number of occurrences
    // return the max

    static class Pair {
        String web;
        int time;
        public Pair(String web, int time) {
            this.web = web;
            this.time = time;
        }
    }

    private static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // map user, pair
        Map<String, List<Pair>> userVisitedMap = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            userVisitedMap.putIfAbsent(username[i], new ArrayList<>());
            userVisitedMap.get(username[i]).add(new Pair(website[i], timestamp[i]));
        }

        // count number of sequences
        Map<String, Integer> count = new HashMap<>();
        // keep track of sequence with max count
        String maxSeq = "";

        // go through each user's visit and generate 3 sequence
        for (String s: userVisitedMap.keySet()) {
            Set<String> seqSet = new HashSet<>(); // unique sequences for this user
            // get all visits from this user
            List<Pair> visits = userVisitedMap.get(s);
            // sort by time
            Collections.sort(visits, (a, b) -> a.time - b.time); // sort by first visited
            // traverse to generate 3 sequences
            for (int i = 0; i < visits.size(); i++) {
                for (int j = i + 1; j < visits.size(); j++) {
                    for (int k = j + 1; k < visits.size(); k++) {
                        // sequence
                        String seq = visits.get(i).web + " " + visits.get(j).web + " " + visits.get(k).web;
                        if (!seqSet.contains(seq)) {
                            seqSet.add(seq);
                            count.put(seq, count.getOrDefault(seq, 0) + 1);
                        }
                        // store global max visit to string
                        if (maxSeq.equals("") || count.get(maxSeq) < count.get(seq)
                                || (count.get(maxSeq).equals(count.get(seq)) && maxSeq.compareTo(seq) > 0)) { // lexicographically ordered if equal
                            maxSeq = seq;
                        }
                    }
                }
            }
        }

        // grab web visits from the max sequence
        return Arrays.asList(maxSeq.split(" "));
    }
}
