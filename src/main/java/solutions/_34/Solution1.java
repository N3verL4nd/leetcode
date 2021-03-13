package solutions._34;

import java.util.Arrays;

/**
 * 34. Search for a Range
 */
public class Solution1 {

    private int BinarySearchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int BinarySearchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;

        int left = BinarySearchLeft(nums, target);
        int right = BinarySearchRight(nums, target);
        if (left < 0 || left >= nums.length || nums[left] != target) {
            return arr;
        }
        arr[0] = left;
        arr[1] = right;
        return arr;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[] arr = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(solution.searchRange(arr, 8)));
    }
}