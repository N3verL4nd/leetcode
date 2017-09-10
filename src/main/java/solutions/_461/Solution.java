package solutions._461;

/**
 * 461. Hamming Distance
 */
public class Solution {
    public int hammingDistance(int x, int y) {
        int result = 0;
        while (x != 0 || y != 0) {
            if ((x & 0x1) != (y & 0x1)) {
                result++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hammingDistance(1, 3));
    }
}