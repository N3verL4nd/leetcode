package solutions._39;

import java.util.*;

/**
 * 39. Combination Sum
 */
class NewSolution {
    private List<Integer> cur;
    private List<List<Integer>> result;

    private void DFS(int[] arr, int target, int pos) {
        if (target < 0) {
            return;
        }
        if (0 == target) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = pos; i < arr.length; i++) {
            cur.add(arr[i]);
            DFS(arr,target - arr[i], i);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        cur = new LinkedList<>();
        result = new LinkedList<>();
        Arrays.sort(candidates);
        DFS(candidates, target, 0);
        return result;
    }

    public static void main(String[] args) {
        NewSolution solution = new NewSolution();
        int[] arrs = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> list = solution.combinationSum(arrs, target);
        System.out.println(list);
    }
}