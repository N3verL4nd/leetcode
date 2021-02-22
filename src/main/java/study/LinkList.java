package study;

class LinkNode {
    int val;
    LinkNode next;

    public LinkNode(int val) {
        this.val = val;
    }
}

public class LinkList {

    /**
     * 尾插法建立链表
     *
     * @param arr
     * @return
     */
    public static LinkNode create(int... arr) {
        LinkNode head = new LinkNode(arr[0]);
        head.next = null;
        LinkNode tail = head;

        for (int i = 1; i < arr.length; i++) {
            LinkNode node = new LinkNode(arr[i]);
            node.next = null;
            tail.next = node;
            tail = node;
        }
        return head;
    }

    /**
     * 遍历链表
     *
     * @param head
     */
    public static void show(LinkNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        LinkNode list = create(arr);
        show(list);
    }
}
