package solutions._26;

/**
 * 26. Remove Duplicates from Sorted Array
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int pos = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            nums[pos++] = nums[i];
        }
        return pos;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 1, 1, 2, 2, 3, 3};
        System.out.println(solution.removeDuplicates(arr));
    }
}