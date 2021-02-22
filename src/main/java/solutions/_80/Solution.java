package solutions._80;

import java.util.Arrays;

/**
 * 80. Remove Duplicates from Sorted Array II
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 1;
        int count = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i - 1] == nums[j]) {
                count++;
                if (count < 2) {
                    nums[i++] = nums[j];
                }

            } else if (nums[i - 1] < nums[j]) {
                count = 0;
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 1, 1, 2, 2, 3};
        System.out.println(solution.removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }
}