package solutions._172;

import java.math.BigInteger;

class Solution {
    public long trailingZeroes(int n) {
        BigInteger sum = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            sum = sum.multiply(BigInteger.valueOf(i));
        }
        int result = 0;
        String s = sum.toString();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            System.out.println(i + " " + new Solution().trailingZeroes(i));
        }

    }
}