package solutions._119;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 */
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        int[][] arr = new int[rowIndex + 1][];
        for (int i = 0; i <= rowIndex; i++) {
            arr[i] = new int[i + 1];
        }
        if (rowIndex == 0) {
            arr[0][0] = 1;
        } else if (rowIndex == 1) {
            arr[0][0] = 1;

            arr[1][0] = 1;
            arr[1][1] = 1;
        } else {

            arr[0][0] = 1;

            arr[1][0] = 1;
            arr[1][1] = 1;

            for (int i = 2; i <= rowIndex; i++) {
                arr[i][0] = 1;
                arr[i][i] = 1;
                for (int j = 1; j < i; j++) {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }

        }

        for (int i = 0; i <= rowIndex; i++) {
            list.add(arr[rowIndex][i]);
        }

        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> list = solution.getRow(8);
        list.forEach(System.out::println);
    }
}