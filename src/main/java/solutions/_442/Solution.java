package solutions._442;

import java.util.*;

/**
 * 442. Find All Duplicates in an Array
 */
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = solution.findDuplicates2(arr);
        System.out.println(list);
        System.out.println(Arrays.toString(arr));
    }
}