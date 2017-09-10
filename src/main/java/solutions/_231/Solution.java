package solutions._231;

/**
 * 231. Power of Two
 */
public class Solution {
    private int LowBit(int x) {
        return  x & (-x);
    }
    public boolean isPowerOfTwo(int n) {
        boolean flag = false;
        if (n <= 0) {
            return false;
        }
        if (n - LowBit(n) == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPowerOfTwo(-16));
    }
}