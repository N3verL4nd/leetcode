package solutions._53;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/classic/problems/maximum-subarray/description/
 */
class Solution {
    public int maxSubArray(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1] + nums[i]) {
                nums[i] = nums[i - 1] + nums[i];
            }
        }

        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sum) {
                sum = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new Solution().maxSubArray(arr));
    }
}