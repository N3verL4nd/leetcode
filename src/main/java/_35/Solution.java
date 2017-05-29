package _35;

public class Solution {
    private int BinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
    public int searchInsert(int[] nums, int target) {
        return BinarySearch(nums, target);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 3, 5, 7};
        System.out.println(solution.searchInsert(arr, 2));
    }
}