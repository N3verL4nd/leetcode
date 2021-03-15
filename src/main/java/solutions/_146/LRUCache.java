package solutions._146;

import java.util.HashMap;

class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    private int capacity;
    private Node head;
    private Node tail;
    private int count;
    private HashMap<Integer, Node> map;


    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.count = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            count++;
            node = new Node(key, value);
            map.put(key, node);
            addToHead(node);

            // 删除尾结点
            if (count > capacity) {
                Node lastNode = removeTail();
                map.remove(lastNode.key);
                count--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private Node removeTail() {
        Node lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    public static void main(String[] args) {

        LRUCache lru = new LRUCache(3);

        lru.put(1, 1);
        lru.put(2, 2);

        System.out.println(lru.get(1));

        lru.put(3, 3);

        System.out.println(lru.get(2));

        lru.put(4, 4);

        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }
}