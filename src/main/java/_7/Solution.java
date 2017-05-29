package _7;

/**
 * 7. Reverse Integer
 */
public class Solution {
    /*public int reverse(int x) {
        boolean flag = false;
        if (x < 0) {
            x = -x;
            flag = true;
        }
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(x));
        stringBuilder.reverse();
        try {
            x = Integer.parseInt(stringBuilder.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        if (flag) {
            x = -x;
        }
        return x;
    }*/

    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            int y = x % 10;
            result = result * 10 + y;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
            x = x / 10;
        }

        return (int)result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverse(1534236469));
    }
}