package solutions._39;

import java.util.*;

/**
 * 39. Combination Sum
 */
class Solution {
    private Set<List<Integer>> set;
    private List<Integer> cur;

    private void DFS(int[] arr, int target, int sum) {
        if (sum > target) {
            return;
        }
        if (target == sum) {
            ArrayList<Integer> list = new ArrayList<>(cur);
            Collections.sort(list);
            set.add(list);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (i != 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            cur.add(arr[i]);
            DFS(arr, target, sum + arr[i]);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        cur = new LinkedList<>();
        set = new HashSet<>();

        Arrays.sort(candidates);

        DFS(candidates, target, 0);

        List<List<Integer>> result = new ArrayList<>();
        result.addAll(set);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrs = {1, 2};
        int target = 4;
        List<List<Integer>> list = solution.combinationSum(arrs, target);
        System.out.println(list);
    }
}