package solutions._78;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. Subsets
 */

class Solution {
    private List<List<Integer>> result;
    private List<Integer> cur;
    private boolean[] visited;

    private void DFS(int[] nums, int n, int pos) {
        if (n == cur.size()) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cur.add(nums[i]);
                DFS(nums, n, i);
                visited[i] = false;
                cur.remove(cur.size() - 1);
            }
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        result = new LinkedList<>();
        cur = new LinkedList<>();
        visited = new boolean[nums.length];
        for (int i = 1; i <= nums.length; i++) {
            DFS(nums, i, 0);
        }
        result.add(new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> list = solution.subsets(nums);
        System.out.println(list);
    }
}