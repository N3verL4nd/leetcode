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
        ListNode p = new ListNode(0);

        while (p != null) {
            
            p = p.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list = ListUtil.createList(1, 2, 3, 3, 4, 4, 5);
        ListNode node = solution.deleteDuplicates(list);
        ListUtil.show(node);
    }
}