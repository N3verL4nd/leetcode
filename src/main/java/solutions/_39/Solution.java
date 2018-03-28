package solutions._39;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. Combination Sum
 */
class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> cur = new LinkedList<>();

    private void DFS(int[] arr, int pos, int curSum, int sum) {
        if (curSum > sum) {
            return;
        }

        if (curSum == sum) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (int i = pos; i < arr.length; i++) {
            cur.add(arr[i]);
            DFS(arr, i, curSum + arr[i], sum);
            cur.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        DFS(candidates, 0, 0, target);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> list = solution.combinationSum(arr, target);
        System.out.println(list);
    }
}