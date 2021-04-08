package solutions._338;

import java.util.Arrays;

class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        int pos = 0;

        for (int i = 0; i <= num; i++) {
            if (i == 0) {
                result[pos++] = 0;
            } else if (i == 1) {
                result[pos++] = 1;
            } else if ((i & (i - 1)) == 0) {
                result[pos++] = 1;
            } else {
                int x = tableSizeFor(i);
                result[pos++] = result[x] + result[i - x];
            }
        }

        return result;
    }

    private int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        n = n >> 1;
        return n + 1;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().countBits(2)));
        System.out.println(Arrays.toString(new Solution().countBits(5)));
    }
}