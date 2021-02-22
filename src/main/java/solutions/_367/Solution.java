package solutions._367;

/**
 * 367. Valid Perfect Square
 */
public class Solution {
    public boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (mid * mid < num) {
                left = mid + 1;
            } else if (mid * mid > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPerfectSquare(2147483647));
        byte x = 21;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(-x));
    }
}