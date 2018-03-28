package solutions._82;

import utils.ListNode;
import utils.ListUtil;

/**
 * 82. Remove Duplicates from Sorted List II
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = new ListNode(-1);
        p.next = null;
        ListNode tail = p;

        while (head != null) {
            ListNode q = head.next;
            while (q != null && head.val == q.val) {
                q = q.next;
            }
            if (q == head.next) {
                head.next = null;
                tail.next = head;
                tail = head;
            }
            head = q;
        }
        return p.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list = ListUtil.createList(1, 2, 2);
        ListNode node = solution.deleteDuplicates(list);
        ListUtil.show(node);
    }
}