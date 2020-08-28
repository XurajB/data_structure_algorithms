package problems.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter {
    // Using hash map
    // O(1), O(N) where N = number of unique log
    // the map size keeps growing, which may not be a good things
    private Map<String, Integer> messages;
    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        messages = new HashMap<>();
    }
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!messages.containsKey(message)) {
            messages.put(message, timestamp);
            return true;
        }
        int lastTime = messages.get(message);
        if (timestamp - lastTime >= 10) {
            messages.put(message, timestamp);
            return true;
        }
        return false;
    }

    // Similar to hit counter
    // only store past 10s information
    // using a different class
    static class Logger {
        private int[] times;
        private Set[] logs;
        public Logger() {
            times = new int[10];
            logs = new HashSet[10];
            for (int i = 0; i < 10; i++) {
                logs[i] = new HashSet<>();
            }
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            int idx = timestamp % 10;

            // time expired, remove it from logs
            if (timestamp != times[idx]) {
                logs[idx].clear();
                times[idx] = timestamp;
            }
            for (int i = 0; i < 10; i++) {
                if (timestamp - times[i] < 10) {
                    if (logs[i].contains(message)) {
                        return false;
                    }
                }
            }
            logs[idx].add(message);
            return true;
        }
    }
}
