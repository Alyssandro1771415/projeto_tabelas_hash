package com.example;

import java.util.LinkedList;

class HashTableClosedAddressing {
    private LinkedList<Integer>[] table;
    private int size;
    private int collisions;

    public HashTableClosedAddressing(int capacity) {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        collisions = 0;
    }

    private int hash(int key) {
        return key % table.length;
    }

    public void insert(int key) {
        int index = hash(key);
        if (!table[index].isEmpty()) {
            collisions++;
        }
        table[index].add(key);
        size++;
    }

    public boolean search(int key) {
        int index = hash(key);
        return table[index].contains(key);
    }

    public int getCollisions() {
        return collisions;
    }
}
