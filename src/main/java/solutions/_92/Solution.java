package solutions._92;

import utils.ListNode;

/**
 * 92. Reverse Linked List II
 */
class Solution {
    // 1 ≤ m ≤ n ≤ List.length
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n) {
            return head;
        }
        int i = 1;
        ListNode pre = head;
        ListNode first = head;

        while (head != null && i < m) {
            pre = head;
            head = head.next;
            i++;
        }

        ListNode last = head;

        if (head == null) {
            return null;
        }
        ListNode p = head.next;
        head.next = null;
        ListNode q;
        i++;

        while (i <= n && p != null) {
            q = p.next;
            p.next = head;
            head = p;
            p = q;
            i++;
        }
        if (m == 1) {
            last.next = p;
            return head;
        }
        pre.next = head;
        last.next = p;
        return first;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode list = new Solution().reverseBetween(node1, 2, 3);
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
    }
}