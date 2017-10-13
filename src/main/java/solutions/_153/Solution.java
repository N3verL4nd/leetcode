package solutions._153;

/**
 * 153. Find Minimum in Rotated Sorted Array
 */
class Solution {
    public int findMin1(int[] nums) {
        int i;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                break;
            }
        }
        if (i == nums.length) {
            return nums[0];
        }
        return nums[i];
    }

    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[right] > nums[left]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {4, 5};
        System.out.println(solution.findMin1(arr));
        System.out.println(solution.findMin2(arr));
    }
}