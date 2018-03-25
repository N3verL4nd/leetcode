package solutions._24;

import utils.ListNode;

/**
 * 24. Swap Nodes in Pairs
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = head;
        ListNode tail = head;

        boolean flag = false;
        ListNode p = head, q;
        while (p != null) {
            q = p.next;
            if (q == null) {
                break;
            }
            ListNode temp = q.next;
            if (!flag) {
                flag = true;
                newHead = q;
                newHead.next = p;
                tail = p;
                p = temp;
                continue;
            }
            tail.next = q;
            q.next = p;
            tail = p;
            p = temp;
        }
        if (p != null) {
            tail.next = p;
            p.next = null;
        } else {
            tail.next = null;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode p = solution.swapPairs(node1);
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}