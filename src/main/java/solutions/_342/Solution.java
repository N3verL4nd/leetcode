package solutions._342;

/**
 * 342. Power of Four
 */
public class Solution {
    private int LowBit(int x) {
        return x & (-x);
    }

    public boolean isPowerOfFour1(int num) {
        if (num <= 0) {
            return false;
        }
        if (num - LowBit(num) != 0) {
            return false;
        }
        num--;
        int result = 0;
        while (num != 0) {
            result++;
            num -= LowBit(num);
        }
        if (result % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPowerOfFour(int num) {
        if ((num & (num - 1)) != 0) {
            return false;
        }
        if ((num - 1) % 3 != 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPowerOfFour(32));
    }
}