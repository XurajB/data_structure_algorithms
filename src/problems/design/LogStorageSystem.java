package problems.design;

import java.util.*;

/**
 * You are given several logs that each log contains a unique id and timestamp.
 * Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 *
 * Design a log storage system to implement the following functions:
 *
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
 *
 * int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end
 * Start and end all have the same format as timestamp. However, granularity means the time level for consideration.
 * For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day",
 * it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 */
public class LogStorageSystem {

    public static void main(String[] args) {
        LogStorageSystem log = new LogStorageSystem();
        log.put(1, "2017:01:01:23:59:59");
        log.put(2, "2017:01:01:22:59:59");
        log.put(3, "2016:01:01:00:00:00");

        System.out.println(log.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"));
    }

    // given the granularity we can set a lower bound and upper bound of timestamp so we can do range query using TreeMap
    // to get the upper bound, we append a suffix of max to the prefix of start and similarly append min to prefix of end

    private String min;
    private String max;
    private HashMap<String, Integer> map; // granularity, index
    private TreeMap<String, LinkedList<Integer>> logs; // in case we have multiple entries at the same time

    LogStorageSystem() {
        min = "2000:01:01:00:00:00";
        max = "2017:12:31:23:59:59";

        map = new HashMap<>();
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);

        logs = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
        if (!logs.containsKey(timestamp)) {
            logs.put(timestamp, new LinkedList<>());
        }
        logs.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int index  = map.get(gra);

        // calculate range
        String start = s.substring(0, index) + min.substring(index);
        String end = e.substring(0, index) + max.substring(index);

        NavigableMap<String, LinkedList<Integer>> sub = logs.subMap(start,true, end, true);
        List<Integer> ans = new LinkedList<>();
        for (Map.Entry<String, LinkedList<Integer>> entry: sub.entrySet()) {
            ans.addAll(entry.getValue());
        }

        return ans;
    }
}
