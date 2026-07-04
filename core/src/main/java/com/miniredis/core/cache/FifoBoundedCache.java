package com.miniredis.core.cache;
import java.util.*;

public class FifoBoundedCache<K, V> implements Cache<K, V>{

    int maxEntries;
    private final Map<K, V> cache = new HashMap<>();
    private final Deque<K> insertionOrder = new ArrayDeque<>();

    FifoBoundedCache(int maxEntries){
        if (maxEntries <= 0)throw new IllegalArgumentException("maxEntries must be positive, got: " + maxEntries);
        this.maxEntries = maxEntries;
    }


    @java.lang.Override
    public V get(K key) {
        return cache.get(key);
    }

    @java.lang.Override
    public V put(K key, V value) {
        if(cache.size() >= maxEntries && !cache.containsKey(key)){
            K removedValue= insertionOrder.pollLast();
            cache.remove(removedValue);
        }
        if(cache.containsKey(key)){
            insertionOrder.remove(key);
        }
        insertionOrder.offerFirst(key);
        return cache.put(key, value);
    }

    @java.lang.Override
    public V remove(K key) {
        insertionOrder.remove(key);
        return cache.remove(key);
    }

    @java.lang.Override
    public long size() {
        return (long)cache.size();
    }
}
