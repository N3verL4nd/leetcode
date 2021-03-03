package solutions._328;

import utils.ListNode;
import utils.ListUtil;

/**
 * 328. Odd Even Linked List
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode head1 = new ListNode(-1);
        ListNode head2 = new ListNode(-1);
        head1.next = null;
        head2.next = null;
        ListNode tail1 = head1;
        ListNode tail2 = head2;

        int pos = 1;
        while (head != null) {
            ListNode p = head.next;
            if (pos % 2 == 1) {
                head.next = null;
                tail1.next = head;
                tail1 = head;
            } else {
                head.next = null;
                tail2.next = head;
                tail2 = head;
            }
            head = p;
            pos++;
        }
        tail1.next = head2.next;
        return head1.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list = ListUtil.createList(1, 2, 3, 4, 5);
        ListNode node = solution.oddEvenList(list);
        ListUtil.print(node);
    }
}