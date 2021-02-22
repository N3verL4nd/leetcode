package solutions._371;

/**
 * 371. Sum of Two Integers
 * 先相加而不进位 --> 异或操作
 * 处理进位 --> 与操作
 */
public class Solution {
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getSum(a ^ b, (a & b) << 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getSum(23, 31));
    }
}