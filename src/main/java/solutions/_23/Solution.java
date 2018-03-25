package solutions._23;

import utils.ListNode;

/**
 * 23. Merge k Sorted Lists
 */
class Solution {
    private ListNode head;
    private ListNode tail;

    {
        head = new ListNode(0);
        tail = head;
    }

    public int findNode(ListNode[] lists) {
        int minPos = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                if (lists[i].val < minValue) {
                    minValue = lists[i].val;
                    minPos = i;
                }
            }
        }
        return minPos;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int count = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                ListNode p = lists[i];
                while (p != null) {
                    count++;
                    p = p.next;
                }

            }
        }
        for (int i = 0; i < count; i++) {
            int minNode = findNode(lists);
            tail.next = lists[minNode];
            tail = lists[minNode];
            lists[minNode] = lists[minNode].next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}