package solutions._206;

import utils.ListNode;
import utils.ListUtil;

/**
 * 206. Reverse Linked List
 */
class Solution {
    public static ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head.next;
        head.next = null;
        while (p != null) {
            ListNode q = p.next;
            p.next = head;
            head = p;
            p = q;
        }
        return head;
    }

    /**
     * 递归链表逆置
     *
     * head: 1
     * 1 -> 2 -> 3 -> 4 -> 5
     *  5 -> 4 -> 3 -> 2    1 -> 2
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {

        ListNode list = ListUtil.createList(1, 2, 3, 4, 5);
        ListUtil.print(list);

        ListNode head = new Solution().reverseList(list);
        ListUtil.print(head);
    }
}