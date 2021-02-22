package solutions._81;

/**
 * 81. Search in Rotated Sorted Array II
 */
class Solution {
    private int BinarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int i;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                break;
            }
        }
        int x = BinarySearch(nums, 0, i - 1, target);
        int y = BinarySearch(nums, i, nums.length - 1, target);
        if (x != -1 || y != -1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(arr, 0));
    }
}