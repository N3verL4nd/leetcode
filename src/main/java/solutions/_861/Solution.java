package solutions._861;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/
 */
class Solution {
    public int matrixScore(int[][] arr) {
        int sum = 0;
        int m = arr.length;
        int n = arr[0].length;
        for (int[] ints : arr) {
            if (ints[0] == 0) {
                for (int i = 0; i < ints.length; i++) {
                    ints[i] = ints[i] ^ 1;
                }
            }
        }


        for (int i = 0; i < n; i++) {
            // 每一列 1 的个数
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (arr[j][i] == 1) {
                    count++;
                }
            }

            if (count < m - count) {
                for (int j = 0; j < m; j++) {
                    arr[j][i] = arr[j][i] ^ 1;
                }
            }
        }

        for (int[] ints : arr) {
            String binaryString = Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining());
            sum += Integer.parseInt(binaryString, 2);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] arr = new int[3][];
        arr[0] = new int[]{0, 0, 1, 1};
        arr[1] = new int[]{1, 0, 1, 0};
        arr[2] = new int[]{1, 1, 0, 0};
        int sum = new Solution().matrixScore(arr);
        System.out.println(sum);
    }
}