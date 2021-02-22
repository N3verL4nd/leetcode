package solutions._136;

/**
 * 136. Single Number
 */
public class Solution {
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        System.out.println(new Solution().singleNumber(arr));
    }
}
