package problems.graph;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 */
public class ReconstructItinerary {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        System.out.println(findItinerary(tickets));
    }

    static Map<String, PriorityQueue<String>> map = new HashMap<>();
    static LinkedList<String> ans = new LinkedList<>();
    private static List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket: tickets) {
            map.putIfAbsent(ticket.get(0), new PriorityQueue<>(String::compareTo));
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs("JFK");
        return ans;
    }

    private static void dfs(String departure) {
        PriorityQueue<String> arrivals = map.get(departure);
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll());
        }
        ans.addFirst(departure);
    }
}
