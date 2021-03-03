package utils;

public class ListUtil {
    /**
     * 尾插法建立单链表(无头结点)
     *
     * @param arr 链表元素
     * @return
     */
    public static ListNode createList(int... arr) {
        ListNode head = new ListNode(arr[0]);
        head.next = null;
        ListNode tail = head;

        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            node.next = null;
            tail.next = node;
            tail = node;
        }
        return head;
    }

    /**
     * 遍历链表
     *
     * @param head 头结点
     */
    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
