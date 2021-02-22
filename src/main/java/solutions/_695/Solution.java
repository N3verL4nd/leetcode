package solutions._695;

/**
 * 695. Max Area of Island
 */
class Solution {
    private int m;
    private int n;
    private int max;
    private int cur;
    private static int[][] moves = new int[][]{
            {0, -1},    // 左
            {0, 1},     // 右
            {-1, 0},    // 上
            {1, 0},     // 下
    };

    private void DFS(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        cur++;
        for (int k = 0; k < 4; k++) {
            int p = i + moves[k][0];
            int q = j + moves[k][1];
            if (p >= 0 && p < m && q >= 0 && q < n && grid[p][q] == 1) {
                DFS(grid, p, q);
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cur = 0;
                    DFS(grid, i, j);
                    if (cur > max) {
                        max = cur;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };

        System.out.println(solution.maxAreaOfIsland(grid));
    }
}