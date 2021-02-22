package solutions._2;

import utils.ListNode;

/**
 * 2. Add Two Numbers
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int result;
        boolean flag = false;
        result = l1.val + l2.val;
        if (result >= 10) {
            flag = true;
            result = result - 10;
        }
        ListNode head = new ListNode(result);
        head.next = null;
        ListNode tail = head;
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null && l2 != null) {
            if (flag) {
                result = l1.val + l2.val + 1;
            } else {
                result = l1.val + l2.val;
            }
            if (result >= 10) {
                flag = true;
                result = result - 10;
            } else {
                flag = false;
            }
            ListNode node = new ListNode(result);
            node.next = null;
            tail.next = node;
            tail = node;
            l1 = l1.next;
            l2 = l2.next;
        }

        // 只有一个链表没有遍历完，或者都遍历完
        ListNode p;
        p = (l1 == null ? l2 : l1);

        while (p != null) {
            if (flag) {
                result = p.val + 1;
            } else {
                result = p.val;
            }
            if (result >= 10) {
                flag = true;
                result = result - 10;
            } else {
                flag = false;
            }
            ListNode node = new ListNode(result);
            node.next = null;
            tail.next = node;
            tail = node;
            p = p.next;
        }

        // 判断最高位是否有进位
        if (flag) {
            ListNode node = new ListNode(1);
            node.next = null;
            tail.next = node;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

    }
}