package solutions._191;

/**
 * 191. Number of 1 Bits
 */
public class Solution {
    //借用下树状数组=^=
    int LowBit(int x) {
        return x & (-x);
    }

    public int hammingWeight1(int n) {
        int result = 0;
        while (n != 0) {
            result++;
            n = n - LowBit(n);
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
        Solution solution = new Solution();
        for (int i = 0; i <= 16; i++) {
            System.out.println(i + " " + solution.hammingWeight(i));
        }
    }
}