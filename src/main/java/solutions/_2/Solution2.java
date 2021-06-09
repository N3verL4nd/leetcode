package solutions._2;

import utils.ListNode;

/**
 * 2. Add Two Numbers
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode();
        ListNode tail = head;

        int plus = 0;
        while (l1 != null || l2 != null) {
            int val = 0;
            if (l1 != null && l2 != null) {
                val = l1.val + l2.val + plus;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                val = l1.val + plus;
                l1 = l1.next;
            } else {
                val = l2.val + plus;
                l2 = l2.next;
            }
            plus = val / 10;
            ListNode newNode = new ListNode(val % 10);
            tail.next = newNode;
            tail = newNode;
        }
        if (plus != 0) {
            ListNode newNode = new ListNode(plus);
            tail.next = newNode;
        }
        return head.next;
    }
}