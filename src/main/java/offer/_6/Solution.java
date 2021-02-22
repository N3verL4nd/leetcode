package offer._6;

import utils.ListNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>(10005);
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        int[] result = new int[stack.size()];
        int pos = 0;
        while (!stack.isEmpty()) {
            result[pos++] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node2.next = node5;
        node5.next = node1;
        node1.next = node3;
        node3.next = node4;
        node4.next = null;

        int[] arr = new Solution().reversePrint(node2);
        System.out.println(Arrays.toString(arr));

    }
}

/*

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
示例 1：
输入：head = [1,3,2]
输出：[2,3,1]

限制：
0 <= 链表长度 <= 10000
 */