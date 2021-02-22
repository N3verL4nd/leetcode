package solutions._33;

/**
 * 33. Search in Rotated Sorted Array
 */
class Solution {

    private int BinarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
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

        int left = 0;
        int right = nums.length - 1;
        // 数组有序
        if (nums[left] < nums[right]) {
            return BinarySearch(nums, left, right, target);
        }
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }


        int x = BinarySearch(nums, 0, left, target);
        int y = BinarySearch(nums, right, nums.length - 1, target);
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