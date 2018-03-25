package solutions._234;

import utils.ListNode;

/**
 * 234. Palindrome Linked List
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 奇数个元素
        boolean odd = true;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null) {
                odd = false;
                break;
            }
        }

        ListNode pre;
        if (odd) {
            pre = slow.next;
        } else {
            pre = slow;
        }

        ListNode p = pre.next;
        pre.next = null;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }

        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        System.out.println(new Solution().isPalindrome(node1));
    }

}