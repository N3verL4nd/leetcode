package solutions._60;

/**
 * 60. Permutation Sequence
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] visited;
    private List<String> list;
    private StringBuilder cur;

    public Solution() {
        visited = new boolean[10];
        list = new ArrayList<>();
        cur = new StringBuilder();
        DFS(9, 0);
    }

    private void DFS(int n, int len) {
        if (len == n) {
            list.add(cur.toString());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cur.append(String.valueOf(i).charAt(0));
                DFS(n, len + 1);
                cur.delete(cur.length() - 1, cur.length());
                visited[i] = false;
            }
        }
    }

    public String getPermutation(int n, int k) {
        String temp = list.get(k - 1);
        return temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getPermutation(3, 4));
    }
}