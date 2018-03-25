package solutions._477;

/**
 * 477. Total Hamming Distance
 */
class Solution {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        int x, y;
        for (int i = 0; i < 32; i++) {
            x = 0;
            y = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & 1) == 1) {
                    x++;
                } else {
                    y++;
                }
                nums[j] >>= 1;
            }
            if (x == 0 || y == 0) {
                continue;
            }
            sum += x * y;
        }
        return sum;
    }
}