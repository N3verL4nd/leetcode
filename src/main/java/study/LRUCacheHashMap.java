package study;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liguanghui02
 * @date 2021/3/14
 */
public class LRUCacheHashMap<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCacheHashMap(int initialCapacity, int capacity) {
        super(16, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return super.getOrDefault(key, defaultValue);
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }
}
