package solutions._45;

import java.util.Arrays;

/**
 * 贪心 55－45
 */
class Solution {
    public int jump(int[] nums) {

        if (nums.length <= 1) {
            return 0;
        }

        int[] jump = new int[nums.length];
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            jump[i] = maxJump;
        }
        System.out.println(Arrays.toString(jump));

        int result = 1;
        for (int i = 0; i < jump.length; ) {
            if (jump[i] >= jump.length - 1) {
                break;
            }
            int maxPos = i + 1;
            for (int j = i + 1; j <= jump[i]; j++) {
                if (j >= jump.length) {
                    break;
                }
                if (jump[j] > jump[maxPos]) {
                    maxPos = j;
                }
            }
            i = maxPos;
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new Solution().jump(new int[]{3, 0, 8, 2, 0, 0, 1}));
        System.out.println(new Solution().jump(new int[]{0}));
        System.out.println(new Solution().jump(new int[]{1, 2}));
        System.out.println(new Solution().jump(new int[]{2, 3, 1}));
        System.out.println(new Solution().jump(new int[]{7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3}));
        System.out.println(new Solution().jump(new int[]{1, 1, 1, 1, 1}));
    }
}

/*

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

示例:

输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
    从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
说明:

假设你总是可以到达数组的最后一个位置。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/jump-game-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */