package solutions._130;

import java.util.Arrays;

class Solution {

    private final int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;

    private void dfs(char[][] board, int x, int y) {
        board[x][y] = 'o';
        for (int i = 0; i < direction.length; i++) {
            if (x + direction[i][0] >= 0 && x + direction[i][0] < m && y + direction[i][1] >= 0 && y + direction[i][1] < n && board[x + direction[i][0]][y + direction[i][1]] == 'O') {
                dfs(board, x + direction[i][0], y + direction[i][1]);
            }
        }
    }

    public void solve(char[][] board) {
        if (board.length <= 1) {
            return;
        }

        m = board.length;
        n = board[0].length;

        // 第一行
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
        }
        // 最后一行
        for (int i = 0; i < n; i++) {
            if (board[m - 1][i] == 'O') {
                dfs(board, m - 1, i);
            }
        }

        // 第一列
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
        }

        // 最后一列
        for (int i = 0; i < m; i++) {
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }
//        System.out.println(Arrays.deepToString(board));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
//        System.out.println(Arrays.deepToString(board));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'o') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] arr = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
        new Solution().solve(arr);
        System.out.println(Arrays.deepToString(arr));


        arr = new char[][]{{'X', 'O', 'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X', 'O', 'X'}};
        new Solution().solve(arr);
        System.out.println(Arrays.deepToString(arr));

    }
}

/*

X X X X
X O O X
X X O X
X O X X

 */