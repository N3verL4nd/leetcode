package solutions._61;

import utils.ListNode;
import utils.ListUtil;

/**
 * 61. Rotate List
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }
        int length = 0;
        ListNode p = head;
        ListNode tail = head;
        while (p != null) {
            tail = p;
            length++;
            p = p.next;
        }

        k = k % length;
        // 原地旋转
        if (k == 0) {
            return head;
        }

        p = head;
        ListNode pre = head;
        for (int i = 0; i < length - k; i++) {
            pre = p;
            p = p.next;
        }
        pre.next = null;

        while (head != null) {
            ListNode node = head;
            head = head.next;
            node.next = null;
            tail.next = node;
            tail = node;

        }
        return p;
    }

    public static void main(String[] args) {
        ListUtil.print(new Solution().rotateRight(ListUtil.createList(1, 2, 3, 4, 5), 2));
        ListUtil.print(new Solution().rotateRight(ListUtil.createList(0, 1, 2), 4));
        ListUtil.print(new Solution().rotateRight(ListUtil.createList(0), 4));
    }
}


/*
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例1:
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

示例2:
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步:0->1->2->NULL
向右旋转 4 步:2->0->1->NULL

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */