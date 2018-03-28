package test;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 */

class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> cur = new ArrayList<>();
    private int k;
    private int n;

    private void DFS(int len, int curSum, int pos) {
        if (len > n) {
            return;
        }
        if (curSum > n) {
            return;
        }
        if (curSum == n && k == len) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = pos + 1; i <= 9; i++) {
            cur.add(i);
            DFS(len + 1, i + curSum, i);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n <= 0) {
            return result;
        }
        this.k = k;
        this.n = n;
        DFS(0, 0, 0);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum3(3, 7));
        System.out.println(solution.combinationSum3(3, 9));
    }
}