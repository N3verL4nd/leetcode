package solutions._153;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * 数组没有重复元素
 */
class Solution {

    public int findMin(int... nums) {
        int left = 0;
        int right = nums.length - 1;

        // 数组是有序的
        if (nums[right] > nums[left]) {
            return nums[left];
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println("left = " + left);
        System.out.println("right = " + right);
        return nums[right];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(3, 4, 5, 1, 2));
        System.out.println(solution.findMin(4, 5, 6, 7, 0, 1, 2));
    }
}