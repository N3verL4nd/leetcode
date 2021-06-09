package solutions._234;

import utils.ListNode;
import utils.ListUtil;

/**
 * 234. Palindrome Linked List
 */
class Solution2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;

        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }

        // 奇数个元素
        if (fast != null) {
            pre = pre.next;
        }

        ListNode headTwo = pre.next;
        pre.next = null;

        headTwo = reverseList(headTwo);

        while (headTwo != null && head != null) {
            if (head.val != headTwo.val) {
                return false;
            }
            headTwo = headTwo.next;
            head = head.next;
        }

//        ListUtil.print(headTwo);
        return true;
    }

    public ListNode reverseList(ListNode head) {
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

    public static void main(String[] args) {
        System.out.println(new Solution2().isPalindrome(ListUtil.createList(1, 2, 3, 2, 1)));
        System.out.println(new Solution2().isPalindrome(ListUtil.createList(1, 2, 3, 3, 2, 1)));
    }

}

/*

1 -> 2 -> 3 -> 2 -> 1

 */