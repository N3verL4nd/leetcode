package solutions._61;

import utils.ListNode;
import utils.ListUtil;

/**
 * 61. Rotate List
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        if (k <= 0) {
            return head;
        }
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        if (k >= len) {
            return head;
        }

        // 逆置
        if (k == len - 1) {
            p = head.next;
            head.next = null;
            while (p != null) {
                ListNode q = p.next;
                p.next = head;
                head = p;
                p = q;
            }
            return head;
        }

        p = head;
        while (p != null && k > 0) {
            p = p.next;
            k--;
        }

        ListNode q = p.next;
        ListNode newHead = q;
        p.next = null;
        while (q != null && q.next != null) {
            q = q.next;
        }
        q.next = head;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode list = ListUtil.createList(1, 2);
        ListUtil.show(list);
        Solution solution = new Solution();
        ListNode head = solution.rotateRight(list, 1);
        ListUtil.show(head);

    }
}