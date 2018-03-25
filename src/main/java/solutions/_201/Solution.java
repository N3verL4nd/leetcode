package solutions._201;

/**
 * 201. Bitwise AND of Numbers Range
 */
class Solution {
    public int rangeBitwiseAnd1(int m, int n) {
        boolean flag;
        int result = 0;

        for (int i = 0; i < 32; i++) {
            flag = false;
            for (int j = m; j <= n; j++) {
                if ((j & (1 << i)) == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result |= (1 << i);
            }
        }
        return result;
    }

    /**
     * m != n --> 最末尾必定为 0
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            count++;
        }
        System.out.println("count = " + count);
        System.out.println("m = " + m);
        return m << count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rangeBitwiseAnd(5, 7));

    }
}