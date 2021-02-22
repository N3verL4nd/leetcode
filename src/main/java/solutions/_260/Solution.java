package solutions._260;

import java.util.Arrays;

/**
 * 260. Single Number III
 */
class Solution {
    public int[] singleNumber(int[] nums) {
        int flag = 0;
        int[] result = new int[2];
        for (int num : nums) {
            flag ^= num;
        }
        // flag 二进制中肯定有一个是1
        // 获得最末尾的 1
        flag = flag & (-flag);
        for (int num : nums) {
            if ((flag & num) != 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 2, 5};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.singleNumber(arr)));
    }
}