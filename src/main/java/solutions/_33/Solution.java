package solutions._33;

/**
 * 33. Search in Rotated Sorted Array
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

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int i;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                break;
            }
        }
        int x = BinarySearch(nums, 0, i - 1, target);
        int y = BinarySearch(nums, i, nums.length - 1, target);
        if (x == -1 && y == -1) {
            return -1;
        } else if (x != -1) {
            return x;
        } else {
            return y;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int pos = solution.search(arr, 0);
        System.out.println(pos);
    }
}