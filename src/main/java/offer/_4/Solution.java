package offer._4;

class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) {
            return false;
        }
        int m = matrix[0].length;
        if (m == 0) {
            return false;
        }

        int i = 0;
        int j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

//        System.out.println(new Solution().findNumberIn2DArray(arr, 5));
//        System.out.println(new Solution().findNumberIn2DArray(arr, 20));

        int[][] arr1 = new int[][]
                {
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 25}};

        System.out.println(new Solution().findNumberIn2DArray(arr1, 19));
    }
}

/*

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
示例:
现有矩阵 matrix 如下：
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target=5，返回true。
给定target=20，返回false。
限制：
0 <= n <= 1000
0 <= m <= 1000



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */