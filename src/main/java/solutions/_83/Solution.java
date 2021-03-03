package solutions._83;

import utils.ListNode;
import utils.ListUtil;

/**
 * 83. Remove Duplicates from Sorted List
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head.next;
        ListNode tail = head;
        while (p != null) {
            if (tail.val != p.val) {
                tail.next = p;
                tail = p;
            }
            p = p.next;
        }
        tail.next = null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3};
        Solution solution = new Solution();
        ListNode list = ListUtil.createList(arr);
        list = solution.deleteDuplicates(list);
        ListUtil.print(list);
    }
}