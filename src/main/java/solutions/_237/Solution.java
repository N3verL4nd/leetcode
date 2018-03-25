package solutions._237;

import utils.ListNode;

/**
 * 237. Delete Node in a Linked List
 */
class Solution {
    public void deleteNode(ListNode node) {
        if (node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2, 3, 4};
        ListNode list = new ListNode(arr[0]);
        ListNode tail = list;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            node.next = null;
            tail.next = node;
            tail = node;
        }
        tail.next = null;

        solution.deleteNode(list);

        while (list != null) {
            System.out.println(list.val);
            list = list.next;
        }
    }
}