package solutions._77;

/**
 * 77. Combinations
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> result;
    private List<Integer> cur;
    private int n;
    private int k;

    private void DFS(int pos, int len) {
        if (len == k) {
            result.add(new ArrayList<>(cur));
            return;
        }
        // pos + 1 是为了使每个元素只使用一次
        for (int i = pos + 1; i <= n; i++) {
            cur.add(i);
            DFS(i, len + 1);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        cur = new LinkedList<>();
        result = new LinkedList<>();
        this.n = n;
        this.k = k;
        DFS(0, 0);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combine(4, 2);
        System.out.println(lists);
    }
}