package problems.design;

import java.util.LinkedList;

/**
 * Design a HashSet without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 */
public class HashSet {
    // Two things to remember: hash functions, collision handling
    // the goal of the hash function is to assign an address to store a given value, each unique values should have a unique hash value
    // collision: it can be possible for multiple values mapped to same hash value, we need to a strategy to handle such case. there are several ways to handle this
    // separate chaining - for the values with same hash key, we keep them in a bucket

    // strategy: linkedlist as bucket
    // O(n/k), O(k+m) where k = buckets, m = number of unique values in hashset
    // TODO BST as bucket O(log(n/k))
    class MyHashSet1 {
        private Bucket[] buckets;
        private int keyRange;

        MyHashSet1() {
            this.keyRange = 769; // use a prime number to reduce collision
            this.buckets = new Bucket[keyRange];
            for (int i = 0; i < keyRange; i++) {
                buckets[i] = new Bucket();
            }
        }

        private int hash(int key) {
            return key % keyRange;
        }

        public void add(int key) {
            int index = hash(key);
            buckets[index].insert(key);
        }

        public void remove(int key) {
            int index = hash(key);
            buckets[index].delete(key);
        }

        public boolean contains(int key) {
            int index = hash(key);
            return this.buckets[index].exists(key);
        }

        class Bucket {
            private LinkedList<Integer> container;
            Bucket() {
                container = new LinkedList<>();
            }
            public void insert(Integer key) {
                int index = container.indexOf(key);
                if (index == -1) {
                    container.addFirst(key);
                }
            }
            public void delete(Integer key) {
                container.remove(key);
            }
            public boolean exists(Integer key) {
                int index = container.indexOf(key);
                return index != -1;
            }
        }
    }
}
