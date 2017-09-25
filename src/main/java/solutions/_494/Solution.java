package solutions._494;

class Solution {
    private int count;
    private int target;

    private void DFS(int[] nums, int i, int s) {
        if (i == nums.length) {
            if (s == target) {
                count++;
            }
            return;
        }

        DFS(nums, i + 1, s + nums[i]);

        DFS(nums, i + 1, s - nums[i]);
    }

    public int findTargetSumWays(int[] nums, int S) {
        count = 0;
        target = S;
        DFS(nums, 0, 0);
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution.findTargetSumWays(arr, target));
    }
}