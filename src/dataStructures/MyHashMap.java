package dataStructures;

/**
 * Created by Xuraj on 11/12/2019.
 *
 * Java implementation - HashSet, HashTable, HashMap
 *
 * HashTable and HashMap are essentially the same except that HashTable is synchronized and HashMap allows nulls (key and value)
 *
 * Key concepts - hash function, collision, load factor
 */
public class MyHashMap<K, V> {
    private int capacity;
    private Entry<K, V>[] buckets;

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        buckets = new Entry[capacity];
    }

    private int getHash(int key) {
        int hash = key % capacity;
        if (hash < 0) {
            hash += capacity;
        }
        return hash;
    }

    // this implementation assumes the key is always integer, in real world it can be anything
    public void put(K key, V value) {
        int hash = getHash(key.hashCode());
        Entry<K, V> existing = buckets[hash];
        Entry<K, V> entry = new Entry<>(key, value);

        if (existing == null) {
            buckets[hash] = entry;
        } else { // using separating chaining collision resolution. Another way is open addressing, resizing the array to reducing collision.
            while(existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }
            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
            }
        }
    }

    public V get(K key) {
        int hash = getHash(key.hashCode());

        Entry<K, V> bucket = buckets[hash];
        while (bucket != null) {
            if (key == bucket.key) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    public void delete(K key) {
        int hash = getHash(key.hashCode());
        Entry<K, V> bucket = buckets[hash];
        Entry<K, V> previousEntry = null;

        // Case 1 - first in the bucket
        if (bucket != null && bucket.key == key) {
            buckets[hash] = bucket.next;
            System.out.print("Removed data");
            return;
        }

        // Case 2 - somewhere in the bucket
        while (bucket != null && bucket.key != key) {
            previousEntry = bucket;
            bucket = bucket.next;
        }

        // found
        if (bucket != null) {
            previousEntry.next = bucket.next;
            buckets[hash] = previousEntry;
            System.out.print("Removed data");
        }

        // Case 3 - not found
        if (bucket == null) {
            System.out.print("Data not found");
        }
    }

    public void print() {
        for (Entry<K, V> entry: buckets) {
            while (entry != null) {
                System.out.print(entry.key + " ");
                entry = entry.next;
            }
            System.out.println();
        }
    }

    private class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
