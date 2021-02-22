package solutions._74;

import jdk.nashorn.internal.ir.IfNode;

class Solution {
    private boolean BinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i;
        int row = -1;
        for (i = 0; i < matrix.length - 1; i++) {
            if (target >= matrix[i][0] && target < matrix[i + 1][0]) {
                row = i;
                break;
            }
        }
        if (row == -1) {
            row = i;
        }
        return BinarySearch(matrix[row], target);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{ {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int target = 3;
        System.out.println(solution.searchMatrix(matrix, target));
    }
}