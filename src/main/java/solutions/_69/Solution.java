package solutions._69;

public class Solution {
    public int mySqrt(int x) {
        int left = 1;
        int right = x;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (mid * mid < x) {
                left = (int)mid + 1;
            } else if (mid * mid > x) {
                right = (int)mid - 1;
            } else {
                return (int) mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(0));
    }
}