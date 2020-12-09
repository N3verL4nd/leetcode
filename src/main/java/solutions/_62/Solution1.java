package solutions._62;

class Solution1 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 最后一行
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }

        // 最后一列
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        System.out.println(new Solution1().uniquePaths(3, 3));
        System.out.println(new Solution1().uniquePaths(4, 3));
        System.out.println(new Solution1().uniquePaths(7, 3));
        System.out.println(new Solution1().uniquePaths(20, 20));
    }
}