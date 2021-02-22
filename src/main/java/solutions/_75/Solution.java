package solutions._75;

import java.util.Arrays;

/**
 * 75. Sort Colors
 */
class Solution {
    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    public void sortColors(int[] nums) {
        int pos = 0;
        int i;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, pos, i);
                pos++;
            }
        }
        for (i = pos; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, pos, i);
                pos++;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 1, 2, 0, 1, 2, 1, 1, 2, 2, 0, 0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}