package solutions._160;

import utils.ListNode;

/**
 * 160. Intersection of Two Linked Lists
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode p = headA;
        ListNode q = headB;
        while (p != null) {
            lenA++;
            p = p.next;
        }
        while (q != null) {
            lenB++;
            q = q.next;
        }

        ListNode longList, shortList;
        int len;

        if (lenA > lenB) {
            longList = headA;
            shortList = headB;
            len = lenA - lenB;
        } else {
            longList = headB;
            shortList = headA;
            len = lenB - lenA;
        }

        while (len != 0) {
            longList = longList.next;
            len--;
        }

        while (longList != null && shortList != null) {
            if (longList == shortList) {
                return longList;
            }
            longList = longList.next;
            shortList = shortList.next;
        }
        return null;
    }

}