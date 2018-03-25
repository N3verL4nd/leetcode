package solutions._59;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int val = 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = val;
                val++;
            }
            rowStart++;
            if (rowStart > rowEnd) {
                break;
            }

            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = val;
                val++;
            }
            colEnd--;
            if (colEnd < colStart) {
                break;
            }

            for (int i = colEnd; i >= colStart; i--) {
                matrix[rowEnd][i] = val;
                val++;
            }
            rowEnd--;
            if (rowEnd < rowStart) {
                break;
            }

            for (int i = rowEnd; i >= rowStart; i--) {
                matrix[i][colStart] = val;
                val++;
            }
            colStart++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = solution.generateMatrix(3);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}