package _9;

/**
 * 9. Palindrome Number
 */
public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        long result = 0;
        int temp = x;
        while (temp != 0) {
            int y = temp % 10;
            result = result * 10 + y;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return false;
            }
            temp = temp / 10;
        }

        return (int)result == x;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(121));
    }
}