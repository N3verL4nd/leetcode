package solutions._27;

/**
 * 27. Remove Element
 */
public class Solution {
    public int removeElement(int[] nums, int val) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[start++] = nums[i];
            }
        }
        return start;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {3, 2, 2, 3};
        System.out.println(solution.removeElement(arr, 3));
    }
}