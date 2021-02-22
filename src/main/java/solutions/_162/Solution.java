package solutions._162;

class Solution {

    public int findPeakElement1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
           if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
               right = mid;
           }
        }
        return right;
    }

    public int findPeakElement2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2, 1, 2};
        System.out.println(solution.findPeakElement1(arr));
        System.out.println(solution.findPeakElement2(arr));
    }
}