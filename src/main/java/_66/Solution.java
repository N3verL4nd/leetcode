package _66;

import java.util.Arrays;

/**
 * 66. Plus One
 */
public class Solution {
    public int[] plusOne(int[] digits) {
        int plus = 1;
        int[] arr = new int[digits.length];
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + plus >= 10) {
                arr[i] = digits[i] + plus - 10;
                plus = 1;
            } else {
                arr[i] = digits[i] + plus;
                plus = 0;
            }
        }
        if (plus == 0) {
            return arr;
        }
        int[] newArr = new int[digits.length + 1];
        newArr[0] = plus;
        for (int i = 0; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }
        return newArr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 0};
        System.out.println(Arrays.toString(solution.plusOne(arr)));
    }
}