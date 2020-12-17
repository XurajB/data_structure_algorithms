package problems.bfsdfs;

import java.util.HashMap;
import java.util.Map;
/**
 * Given a dictionary dict, write a function flattenDictionary that returns a flattened version of it .
 * If a certain key is empty, it should be excluded from the output (see e in the example below).
 * input:  dict = {
 *             "Key1" : "1",
 *             "Key2" : {
 *                 "a" : "2",
 *                 "b" : "3",
 *                 "c" : {
 *                     "d" : "3",
 *                     "e" : {
 *                         "" : "1"
 *                     }
 *                 }
 *             }
 *         }
 *
 * output: {
 *             "Key1" : "1",
 *             "Key2.a" : "2",
 *             "Key2.b" : "3",
 *             "Key2.c.d" : "3",
 *             "Key2.c.e" : "1"
 *         }
 */
public class FlattenDictionary {
    // O(N), O(N)
    static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
        return helper(dict);
    }
    static HashMap<String, String> helper(Map<String, Object> map) {
        HashMap<String, String> cur = new HashMap<>();
        for (String key: map.keySet()) {
            if (map.get(key) instanceof String) {
                cur.put(key, (String) map.get(key));
            } else {
                HashMap<String, String> sub = helper((HashMap<String, Object>) map.get(key));
                for (String key2: sub.keySet()) {
                    if (key2.equals("")) {
                        cur.put(key, (String) sub.get(key2));
                    } else if (key.equals("")) {
                        cur.put(key2, (String) sub.get(key2));
                    } else {
                        cur.put(key+"."+key2, (String) sub.get(key2));
                    }
                }
            }
        }
        return cur;
    }
}
