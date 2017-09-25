package solutions._141;

public class Solution {
    public boolean hasCycle(ListNode head) {
        while (head != null) {
            ListNode p = head.next;
            while (p != null) {
                if (p == head) {
                    return true;
                }
                p = p.next;
            }
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;

        System.out.println(new Solution().hasCycle(node1));
    }
}