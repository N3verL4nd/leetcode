package solutions._287;

/**
 * 287. Find the Duplicate Number
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int small = 0;
            for (int num : nums) {
                if (num <= mid) {
                    small++;
                }
            }
            if (small > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(solution.findDuplicate(new int[]{1, 3, 2, 2, 4}));
        System.out.println(solution.findDuplicate(new int[]{1, 3, 5, 2, 4, 5}));
        System.out.println(solution.findDuplicate(new int[]{1, 3, 2, 4, 2, 5, 6}));
        System.out.println(solution.findDuplicate(new int[]{1, 1, 2}));
    }
}