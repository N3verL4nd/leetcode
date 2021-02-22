package solutions._47;

/**
 * 47. Permutations II
 */
import java.util.*;

class Solution {
    private boolean visited[];
    private Set<List<Integer>> sets;
    private List<Integer> cur;

    private void DFS(int[] nums) {
        if (cur.size() == nums.length) {
            sets.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cur.add(nums[i]);
                DFS(nums);
                visited[i] = false;
                cur.remove(cur.size() - 1);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        visited = new boolean[nums.length];
        sets = new HashSet<>();
        cur = new LinkedList<>();
        DFS(nums);
        List<List<Integer>> result = new LinkedList<>();
        result.addAll(sets);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 1, 2};
        List<List<Integer>> lists = solution.permuteUnique(arr);
        System.out.println(lists);
    }
}