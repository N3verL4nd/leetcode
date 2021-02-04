package solutions._1721;

import utils.ListNode;
import utils.ListUtil;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 1; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                break;
            }
        }
        if (fast == null) {
            return head;
        }
        ListNode start = fast;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (slow != null) {
            int temp = start.val;
            start.val = slow.val;
            slow.val = temp;
        }
        return head;
    }

    public static void main(String[] args) {
        ListUtil.show(new Solution().swapNodes(ListUtil.createList(1, 2, 3, 4, 5), 2));
        ListUtil.show(new Solution().swapNodes(ListUtil.createList(7, 9, 6, 6, 7, 8, 3, 0, 9, 5), 5));
        ListUtil.show(new Solution().swapNodes(ListUtil.createList(1), 1));
        ListUtil.show(new Solution().swapNodes(ListUtil.createList(1, 2), 1));
    }
}