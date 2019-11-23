package dataStructures;

import java.util.HashMap;

/**
 * Created by Xuraj on 11/12/2019.
 *
 * Java implementation - HashSet, HashTable, HashMap
 *
 * HashTable and HashMap are essentially the same except that HashTable is synchronized and HashMap allows nulls (key and value)
 *
 * Key concepts - hash function, collision, load factor
 */
public class MyHashMap {
    private int hashSize;
    private MyLinkedList[] buckets;

    public MyHashMap(int hashSize) {
        this.hashSize = hashSize;
        buckets = new MyLinkedList[hashSize];
        for (int i = 0; i < hashSize; i++) {
            buckets[i] = new MyLinkedList();
        }
    }

    public int hashing(int key) {
        int hash = key % hashSize;
        if (hash < 0) {
            hash += hashSize;
        }
        return hash;
    }

    // this implementation assumes the key is always integer, in real world it can be anything
    public void put(int key, int data) {
        int hash = hashing(key);
        buckets[hash].insert(data);
    }

}
