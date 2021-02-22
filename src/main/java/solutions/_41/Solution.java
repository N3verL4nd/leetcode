package solutions._41;

/**
 * 41. First Missing Positive
 */
class Solution {
    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstMissingPositive(new int[]{-1, 1, 3, 4}));
    }
}