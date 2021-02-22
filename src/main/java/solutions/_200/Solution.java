package solutions._200;

/**
 * 200. Number of Islands
 */

class Solution {
    /**
     * {-1, 0}:上
     * {1, 0}:下
     * {0, -1}:左
     * {0, 1}:右
     * */
    private static final int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 标记是不是被访问过
    private boolean visited[][];
    // 长度
    private int m;
    // 宽度
    private int n;

    private void DFS(char[][] grid, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int p = x + move[i][0];
            int q = y + move[i][1];
            if (p >= 0 && p < m && q >= 0 && q < n && !visited[p][q] && grid[x][y] == '1') {
                visited[p][q] = true;
                DFS(grid, p, q);
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    DFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };

        char[][] grid3 = {};
        System.out.println(solution.numIslands(grid1));
    }
}