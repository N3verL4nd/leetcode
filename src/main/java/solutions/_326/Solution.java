package solutions._326;

/**
 * 326. Power of Three
 */
class Solution {
    // 3^19 = 1162261467
    // 2 ^ 31 = 2147483648
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPowerOfThree(27));
        System.out.println(Integer.MAX_VALUE);
        System.out.println((int) Math.pow(2, 31));
    }
}