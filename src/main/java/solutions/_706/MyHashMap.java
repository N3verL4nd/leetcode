package solutions._706;

class MyHashMap {

    public static class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] map;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        map = new Node[100001];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int pos = key % map.length;
        Node head = map[pos];
        if (head == null) {
            Node node = new Node(key, value);
            map[pos] = node;
        } else {
            Node p = head;
            while (p != null) {
                if (p.key == key) {
                    p.value = value;
                    break;
                }
                p = p.next;
            }
            if (p == null) {
                Node node = new Node(key, value);
                node.next = head;
                map[pos] = node;
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int pos = key % map.length;
        Node head = map[pos];
        while (head != null) {
            if (head.key == key) {
                return head.value;
            }
            head = head.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int pos = key % map.length;
        Node head = map[pos];
        if (head == null) {
            return;
        }

        // 头结点
        if (head.key == key) {
            head = head.next;
            map[pos] = head;
            return;
        }

        Node pre = head;
        head = head.next;
        while (head != null) {
            if (head.key == key) {
                break;
            }
            pre = head;
            head = head.next;
        }

        if (head != null) {
            pre.next = head.next;
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(1));    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(3));    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        System.out.println(myHashMap.get(2));    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        System.out.println(myHashMap.get(2));    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
    }
}