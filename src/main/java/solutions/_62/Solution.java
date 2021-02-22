package solutions._62;

/**
 * 62. Unique Paths
 */
class Solution {
    private int[][] dp = new int[105][105];
    private int m;
    private int n;

    private int DFS(int i, int j) {

        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (i >= m || j >= n) {
            return 0;
        }

        if (dp[i][j] != 0) {
            return dp[i][j];
        }


        dp[i + 1][j] = DFS(i + 1, j);
        dp[i][j + 1] = DFS(i, j + 1);
        dp[i][j] = dp[i + 1][j] + dp[i][j + 1];

        return dp[i][j];
    }

    public int uniquePaths(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }
        this.m = m;
        this.n = n;
        return DFS(0, 0);
    }

    /**
     * 人生中第一个 DP
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsDP(int m, int n) {
        if (n == 1 || m == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                }
                if (i + 1 < m && j + 1 < n) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths(3, 7));
        System.out.println(solution.uniquePaths(1, 10));
//        System.out.println(solution.uniquePathsDP(3, 7));
//        System.out.println(solution.uniquePathsDP(1, 10));
    }
}