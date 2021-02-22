package solutions._190;

/**
 * 190. Reverse Bits
 */
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                result = (result << 1) + 1;
            } else {
                result <<= 1;
            }
            n >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseBits(43261596));
        System.out.println(Integer.reverse(43261596));
    }
}