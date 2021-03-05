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

    /**
     * A eulerian graph is a graph containing eulerian cycle. Simple definition: you can draw the graph without taking your pencil off the paper, ending at your starting point
     * Connected graph is eulerian if and only if every vertex has even degree.
     * Eulerian path: that visits every edge exactly once. Eulerian circuit is an eulerian path which starts and ends on the same vertex.
     */
    // ElogV
    private static List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> ans = new LinkedList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (List<String> ticket: tickets) {
            map.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs("JFK", map, ans);
        return ans;
    }
    private static void dfs(String departure, Map<String, PriorityQueue<String>> map, LinkedList<String> ans) {
        PriorityQueue<String> arrivals = map.get(departure);
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll(), map, ans);
        }
        // add the airport to the head of the itinerary
        ans.addFirst(departure);
    }
}
