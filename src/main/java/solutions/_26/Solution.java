package solutions._26;

import java.util.Arrays;

/**
 * 26. Remove Duplicates from Sorted Array
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int pos = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            nums[pos++] = nums[i];
        }
        return pos;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i - 1] < nums[j]) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {};
        System.out.println(solution.removeDuplicates2(arr));
        System.out.println(Arrays.toString(arr));
    }
}