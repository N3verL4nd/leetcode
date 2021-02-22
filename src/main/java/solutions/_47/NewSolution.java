package solutions._47;

import java.util.*;

public class NewSolution {
    private boolean visited[];
    private List<List<Integer>> result;
    private List<Integer> cur;

    private void DFS(int[] nums) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            cur.add(nums[i]);
            DFS(nums);
            visited[i] = false;
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        visited = new boolean[nums.length];
        result = new LinkedList<>();
        cur = new LinkedList<>();
        Arrays.sort(nums);
        DFS(nums);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 1, 2};
        List<List<Integer>> lists = solution.permuteUnique(arr);
        System.out.println(lists);
    }
}
