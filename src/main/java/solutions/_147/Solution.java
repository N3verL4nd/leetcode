package solutions._147;

import utils.ListNode;
import utils.ListUtil;

/**
 * 147. Insertion Sort List
 */
class Solution {

    private ListNode tail;
    private ListNode head;

    /**
     * 将结点 p 插入 已排序链表 head
     *
     * @param p 待插入结点
     */
    private void insert(ListNode p) {
        ListNode first = head;
        ListNode second = head.next;

        // 头插法
        if (p.val <= first.val) {
            p.next = head;
            head = p;
            return;
        }

        // 尾插法
        if (p.val >= tail.val) {
            tail.next = p;
            tail = p;
            return;
        }

        // 插入为中间位置
        while (second != null) {
            if ((first.val <= p.val && p.val <= second.val)) {
                break;
            } else {
                first = second;
                second = second.next;
            }
        }

        p.next = second;
        first.next = p;

    }

    public ListNode insertionSortList(ListNode root) {
        if (root == null) {
            return null;
        }
        ListNode p = root.next;
        root.next = null;
        this.head = root;
        tail = root;
        while (p != null) {
            ListNode q = p.next;
            // 插入结点 p
            p.next = null;
            insert(p);
            p = q;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode list = ListUtil.createList(2, 5, 1, 3, 4);
        list = new Solution().insertionSortList(list);
        ListUtil.print(list);
    }
}