package solutions._876;

import utils.ListNode;
import utils.ListUtil;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListUtil.print(new Solution().middleNode(ListUtil.createList(1, 2, 3, 4, 5)));
        ListUtil.print(new Solution().middleNode(ListUtil.createList(1, 2, 3, 4, 5, 6)));
    }
}