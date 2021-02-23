package study;

import java.util.*;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    // 链表数组长度
    static final int SIZE = 997;

    /**
     * 创建一个链表数组
     * 链表里面存放的是 MapEntry<K, V>
     * 每个 buckets[i] 都对应一个LinkedList<MapEntry<K, V>>
     * 虽然不能创建泛型数组，但是可以创建泛型数组的引用
     */
    @SuppressWarnings("unchecked")
    private LinkedList<Node<K, V>>[] buckets = new LinkedList[SIZE];

    /**
     * 将键值对放入 HashMap
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @Override
    public V put(K key, V value) {
        V oldValue = null;

        // 这里就是通过对键散列然后取余
        // 它代表了对象（LinkedList-->具有相同的索引）在数组里的位置，既数组下标
        int index = Math.abs(key.hashCode()) % SIZE;

        if (buckets[index] == null) {
            // 如果是第一次散列到这个数组下标，那么就生成一个新的 LinkedList，链表里面保存的是 MapEntry<K,V>
            buckets[index] = new LinkedList<>();
        }

        LinkedList<Node<K, V>> bucket = buckets[index];
        // 根据键值对构建 hashMap 里存储的结点
        Node<K, V> node = new Node<>(key, value);

        boolean found = false;
        ListIterator<Node<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            Node<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                // 如果存在旧的键值对，就用新的 value 代替旧的 value; key 保持不变
                oldValue = iPair.getValue();
                it.set(node);
                found = true;
                break;
            }
        }
        if (!found) {
            // 如果是一个新的键值，那么直接添加到这个 LinkedList 中
            buckets[index].add(node);
        }
        return oldValue;
    }

    /**
     * 根据键返回value
     *
     * @param key 键
     * @return 存在该 key 则返回相应的 value；否则返回 null
     */
    @Override
    public V get(Object key) {
        // 获得相应的 LinkedList 对应的索引
        // 为什么要用 LinkedList: 因为 hashcode 方法产生的散列码不能完全确定一个对象(发生碰撞)，即不同的对象散列到同一个数组下标
        // 解决办法: 定义一个 List 把发生碰撞的对象放入其中，然后执行线性查找
        // hashcode()相等的两个对象，equals()不一定相等；而equals()相等的两个对象，hashcode()一定相等
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        for (Node<K, V> node : buckets[index]) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    /**
     * @return 返回键值对所对应的 Set
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Node<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            set.addAll(bucket);
        }
        return set;
    }

    /**
     * 内部存储的结点
     *
     * @param <K> 键
     * @param <V> 值
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                return Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue());
            }
            return false;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> m = new SimpleHashMap<>();
        m.put("Aa", "AAAAA");
        m.put("BB", "BBBBB");
        m.put("CC", "CCCCC");
        System.out.println(m);
        System.out.println(m.get("Aa"));
        System.out.println(m.entrySet());
    }
}