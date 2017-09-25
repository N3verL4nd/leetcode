package solutions._77;

/**
 * 77. Combinations
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private boolean[] visited;
    private List<List<Integer>> result;
    private List<Integer> cur;
    private int n;
    private int k;

    private void DFS(int pos, int len) {
        if (len == k) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = pos; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cur.add(i);
                DFS(i, len + 1);
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        visited = new boolean[n + 1];
        cur = new LinkedList<>();
        result = new LinkedList<>();
        this.n = n;
        this.k = k;
        DFS(1, 0);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combine(4, 2);
        System.out.println(lists);
    }
}