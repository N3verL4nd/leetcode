package solutions._377;

/**
 * 377. Combination Sum IV
 */
class Solution {
    private int count = 0;

    private void DFS(int[] nums, int curSum, int target) {
        if (curSum > target) {
            return;
        }
        if (curSum == target) {
            count++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            DFS(nums, curSum + nums[i], target);
        }
    }

    public int combinationSum4(int[] nums, int target) {
        DFS(nums, 0, target);
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2, 3};
        System.out.println(solution.combinationSum4(arr, 32));
    }
}