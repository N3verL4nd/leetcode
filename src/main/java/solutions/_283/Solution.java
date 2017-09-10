package solutions._283;

import java.util.Arrays;

/**
 * 283. Move Zeroes
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j;
        for (j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {0, 1, 0, 3, 12};
        solution.moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}