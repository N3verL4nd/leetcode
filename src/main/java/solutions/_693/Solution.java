package solutions._693;

/**
 * 693. Binary Number with Alternating Bits
 */
class Solution {
    public boolean hasAlternatingBits(int n) {
        while (n != 0) {
            if ((n & 1) == 1) {
                n = n >> 1;
                if ((n & 1) != 0) {
                    return false;
                }
            } else {
                n = n >> 1;
                if ((n & 1) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hasAlternatingBits(10));
    }
}