package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private List<List<Integer>> result = new ArrayList<>();
    private LinkedList<Integer> cur = new LinkedList<>();
    private boolean visited[];

    private void DFS(int[] arr, int pos, int curSum, int sum) {
        if (curSum == sum) {
            result.add(new ArrayList<>(cur));
            return;
        }
        if (curSum > sum) {
            return;
        }

        for (int i = pos; i < arr.length; i++) {
            if (i > 0 && !visited[i - 1] && arr[i - 1] == arr[i]) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                cur.add(arr[i]);
                DFS(arr, i, curSum + arr[i], sum);
                cur.removeLast();
                visited[i] = false;
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return result;
        }
        Arrays.sort(arr);
        visited = new boolean[arr.length];
        DFS(arr, 0, 0, target);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        Main m = new Main();
        List<List<Integer>> result = m.combinationSum(arr, 8);
        System.out.println(result);
    }
}
