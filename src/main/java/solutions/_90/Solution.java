package solutions._90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. Subsets II
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
            if (visited[i]) {
                continue;
            }
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            cur.add(nums[i]);
            DFS(nums, n, i);
            visited[i] = false;
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result = new LinkedList<>();
        cur = new LinkedList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; i++) {
            DFS(nums, i, 0);
        }
        result.add(new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> list = solution.subsetsWithDup(nums);
        System.out.println(list);
    }
}