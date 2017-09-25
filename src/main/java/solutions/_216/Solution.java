package solutions._216;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 */
class Solution {
    private boolean[] visited;
    private List<List<Integer>> result;
    private List<Integer> cur;

    private void DFS(int k, int sum, int n, int pos) {
        if (cur.size() > k || sum > n) {
            return;
        }
        if (k == cur.size() && sum == n) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = pos + 1; i <= 9; i++) {
            cur.add(i);
            DFS(k, sum + i, n, i);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        visited = new boolean[n + 1];
        result = new ArrayList<>();
        cur = new ArrayList<>();
        DFS(k, 0, n, 0);
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> list = solution.combinationSum3(3, 9);
        System.out.println(list);
    }
}