package solutions._46;

/**
 * 46. Permutations
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private boolean visited[];
    private List<List<Integer>> result;
    private List<Integer> cur;

    private void DFS(int[] nums, int len) {
        if (len == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cur.add(nums[i]);
                DFS(nums, len + 1);
                visited[i] = false;
                cur.remove(cur.size() - 1);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        result = new LinkedList<>();
        cur = new LinkedList<>();
        DFS(nums, 0);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 2};
        List<List<Integer>> lists = solution.permute(arr);
        System.out.println(lists);
    }
}