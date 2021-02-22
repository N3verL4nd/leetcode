package solutions._79;

/**
 * 79. Word Search
 */
class Solution {
    private boolean[][] visited;
    // 上 下 左 右 四个方向
    private static final int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;
    private boolean flag;

    private void DFS(char[][] board, String word, int i, int j, int count) {
        if (count == word.length()) {
            flag = true;
            return;
        }

        for (int k = 0; k < moves.length; k++) {
            int p = i + moves[k][0];
            int q = j + moves[k][1];
            if (!flag && p >= 0 && p < m && q >= 0 && q < n && !visited[p][q] && board[p][q] == word.toCharArray()[count]) {
                visited[p][q] = true;
                DFS(board, word, p, q, count + 1);
                visited[p][q] = false;
            }
        }
    }

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.toCharArray()[0]) {
                    visited[i][j] = true;
                    DFS(board, word, i, j, 1);
                    visited[i][j] = false;
                }
            }
        }
       return flag;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = new char[][]{ {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'} };
        System.out.println(solution.exist(board, "ABCCED"));
        System.out.println(solution.exist(board, "SEE"));
        System.out.println(solution.exist(board, "ABCB"));
    }
}