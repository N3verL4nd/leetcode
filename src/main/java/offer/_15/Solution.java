package offer._15;

public class Solution {
    // you need to treat n as an unsigned value
    // 超时
    public int hammingWeight1(int n) {
        int result = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                result++;
            }
            n = n >> 1;
        }
        return result;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hammingWeight(9));
    }
}