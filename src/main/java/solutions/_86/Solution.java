package solutions._86;

import utils.ListNode;
import utils.ListUtil;

/**
 * 86. Partition List
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode head1 = new ListNode(-1);
        ListNode head2 = new ListNode(-1);
        head1.next = null;
        head2.next = null;
        ListNode tail1 = head1;
        ListNode tail2 = head2;

        while (head != null) {
            ListNode p = head.next;
            if (head.val < x) {
                head.next = null;
                tail1.next = head;
                tail1 = head;
            } else {
                head.next = null;
                tail2.next = head;
                tail2 = head;
            }
            head = p;
        }
        tail1.next = head2.next;
        return head1.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list = ListUtil.createList(1, 4, 3, 2, 5, 2);
        ListNode node = solution.partition(list, 3);
        ListUtil.show(node);
    }
}