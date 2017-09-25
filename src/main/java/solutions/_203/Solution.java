package solutions._203;

/**
 * 203. Remove Linked List Elements
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        ListNode list = new ListNode(arr[0]);
        ListNode tail = list;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            node.next = null;
            tail.next = node;
            tail = node;
        }
        tail.next = null;


        ListNode elements = solution.removeElements(list, 6);
        while (elements != null) {
            System.out.println(elements.val);
            elements = elements.next;
        }
    }
}