package solutions._21;

import utils.ListNode;
import utils.ListUtil;

/**
 * 21. Merge Two Sorted Lists
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head;

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        ListNode tail = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }

        while (l1 != null) {
            tail.next = l1;
            tail = l1;
            l1 = l1.next;
        }

        while (l2 != null) {
            tail.next = l2;
            tail = l2;
            l2 = l2.next;
        }

        return head;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        Solution solution = new Solution();

        ListNode l1 = ListUtil.createList(arr1);
        ListNode l2 = ListUtil.createList(arr2);

        ListUtil.show(l1);
        ListUtil.show(l2);

        ListNode head = solution.mergeTwoLists(l1, l2);
        ListUtil.show(head);


    }
}