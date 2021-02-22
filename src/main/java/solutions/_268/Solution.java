package solutions._268;

/**
 * 268. Missing Number
 */
public class Solution {
    public int missingNumber(int[] nums) {
        int left = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++, left++) {
            result = left ^ nums[i] ^ result;
        }
        return result ^ nums.length;
    }

    public int missingNumber2(int[] nums) {
        int sum = (nums.length + 1) * nums.length / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 3};
        Solution solution = new Solution();
        System.out.println(solution.missingNumber(arr));
    }
}
