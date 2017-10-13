package solutions._118;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 118. Pascal's Triangle
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        int[][] arr = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            arr[i] = new int[i + 1];
        }
        if (numRows == 0) {
            return lists;
        } else if (numRows == 1) {
            arr[0][0] = 1;
        } else if (numRows == 2) {
            arr[0][0] = 1;

            arr[1][0] = 1;
            arr[1][1] = 1;
        } else {

            arr[0][0] = 1;

            arr[1][0] = 1;
            arr[1][1] = 1;

            for (int i = 2; i < numRows; i++) {
                arr[i][0] = 1;
                arr[i][i] = 1;
                for (int j = 1; j < i; j++) {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }

        }

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                list.add(arr[i][j]);
            }
            lists.add(list);
        }

        return lists;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> list = solution.generate(9);
        list.forEach(System.out::println);
    }
}