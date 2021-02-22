package solutions._54;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) {
            return list;
        }
        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                list.add(matrix[rowStart][i]);
            }
            rowStart++;
            if (rowStart > rowEnd) {
                break;
            }

            for (int i = rowStart; i <= rowEnd; i++) {
                list.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (colEnd < colStart) {
                break;
            }

            for (int i = colEnd; i >= colStart; i--) {
                list.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if (rowEnd < rowStart) {
                break;
            }

            for (int i = rowEnd; i >= rowStart; i--) {
                list.add(matrix[i][colStart]);
            }
            colStart++;
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{2, 3}};
        List<Integer> list = solution.spiralOrder(matrix);
        System.out.println(list);
    }
}