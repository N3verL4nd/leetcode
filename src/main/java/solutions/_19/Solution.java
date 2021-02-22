package solutions._19;

import utils.ListNode;

/**
 * 19. Remove Nth Node From End of List
 */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 删除第一个元素
        if (fast == null) {
            // 存在多个元素
            if (slow.next != null) {
                slow.val = slow.next.val;
                slow.next = slow.next.next;
                return head;
            } else {
                // 只存在一个元素
                return null;
            }

        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 因为找到的 slow 代表要删除的元素的前一个元素
        // 所以 slow.next 一定不为空
        if (slow.next.next == null) {
            slow.next = null;
        } else {
            slow.next = slow.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);


        node1.next = node2;
        node2.next = null;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;


        Solution solution = new Solution();
        ListNode p = solution.removeNthFromEnd(node1, 1);
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}

// Given n will always be valid.
// Try to do this in one pass.