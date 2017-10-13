package solutions._41;

/**
 * 41. First Missing Positive
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x : nums) {
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }

        if (min == max) {
            if (min - 1 > 0) {
                return min - 1;
            } else {
                return max + 1;
            }
        }

        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        for (int i = min; i <= max; i++) {
            result ^= i;
        }

        if (result == 0) {
            if (min - 1 > 0) {
                return min - 1;
            } else {
                return max + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(solution.firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(solution.firstMissingPositive(new int[]{1, 1}));
    }
}