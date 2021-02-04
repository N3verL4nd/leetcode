package solutions._1290;

import utils.ListNode;
import utils.ListUtil;


class Solution {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result <<= 1;
            result += head.val;
            head = head.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode list = ListUtil.createList(1, 0, 1);
        System.out.println(new Solution().getDecimalValue(list));
    }
}