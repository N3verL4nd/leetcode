package solutions._154;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * 数组有重复元素
 */
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            // 此时二分查找就无能为力了
            // 比如 1 1 1 0 1 与 1 0 1 1 1
            // 中间位置一个在第一个数组，一个在第二个数组
            // 不能通过 mid 来判断在哪一个数组内
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
            }
            if (nums[mid] >= nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(new int[]{10, 1, 10, 10, 10}));
    }
}