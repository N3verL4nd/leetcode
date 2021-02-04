package solutions._152;

class Solution {
    public int maxProduct2(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > result) {
                result = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                val *= nums[j];
                if (val > result) {
                    result = val;
                }
            }
        }
        return result;
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;
        for (int num : nums) {
            if (num < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(num, imax * num);
            imin = Math.min(num, imin * num);
//            System.out.println("imax = " + imax + "; imin = " + imin);
            max = Math.max(max, imax);
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{2, 3, -2, 4}));
//        System.out.println(new Solution().maxProduct(new int[]{2, 3, -2, 4, -2}));
//        System.out.println(new Solution().maxProduct(new int[]{-2, 0, 1}));
//        System.out.println(new Solution().maxProduct(new int[]{3, -1, 4}));
//        System.out.println(new Solution().maxProduct(new int[]{3, -1, 4}));
        System.out.println(new Solution().maxProduct(new int[]{2, -5, -2, -4, 3}));
    }
}


/*

给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

示例 1:
输入: [2,3,-2,4]
输出: 6
解释:子数组 [2,3] 有最大乘积 6。

示例 2:
输入: [-2,0,-1]
输出: 0
解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 */