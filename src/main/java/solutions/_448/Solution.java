package solutions._448;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 */
class Solution {

    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> list = solution.findDisappearedNumbers(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(list);
    }
}