package solutions._63;

/**
 * 63. Unique Paths II
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = -1;
                } else if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                }
            }
        }

        // 最有一列
        for (int i = m - 1; i >= 0; i--) {
            if (dp[i][n - 1] == -1) {
                for (int j = 0; j <= i; j++) {
                    dp[j][n - 1] = -1;
                }
                break;
            }
        }

        // 最后一行
        for (int j = n - 1; j >= 0; j--) {
            if (dp[m - 1][j] == -1) {
                for (int i = 0; i <= j; i++) {
                    dp[m - 1][i] = -1;
                }
                break;
            }
        }

        // 只有一行
        if (m == 1) {
            for (int i = 0; i < n; i++) {
                if (dp[0][i] == -1) {
                    return 0;
                }
            }
            return 1;
        }

        // 只有一列
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                if (dp[i][0] == -1) {
                    return 0;
                }
            }
            return 1;
        }


        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (dp[i][j] == -1) {
                    continue;
                }
                if (dp[i + 1][j] != -1 && dp[i][j + 1] != -1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                } else if (dp[i + 1][j] == -1 && dp[i][j + 1] == -1) {
                    dp[i][j] = 0;
                } else if (dp[i][j + 1] == -1) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i][j + 1];
                }

            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = {
                {0, 0},
                {1, 1},
                {0, 0}
        };
        System.out.println(solution.uniquePathsWithObstacles(arr));
    }
}