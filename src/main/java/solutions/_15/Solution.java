package solutions._15;

import java.util.*;

/**
 * 15. 3Sum
 */
class Solution {
    private Set<List<Integer>> set;
    private boolean[] visited;
    private int[] arr;

    private void DFS(int[] nums, int cnt, int pos, int sum) {
        if (cnt == 3) {
            if (sum == 0) {
                List<Integer> list = new ArrayList<>();

                int a = Math.min(Math.min(arr[0], arr[1]), arr[2]);
                int c = Math.max(Math.max(arr[0], arr[1]), arr[2]);

                list.add(a);
                list.add(-a-c);
                list.add(c);

                set.add(list);
            }
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = nums[i];
                DFS(nums, cnt + 1, pos + 1, sum + arr[cnt]);
                visited[i] = false;
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        set = new HashSet<>();
        visited = new boolean[nums.length];
        arr = new int[3];

        Arrays.sort(nums);

        DFS(nums, 0, 0, 0);

        List<List<Integer>> lists = new ArrayList<>();
        lists.addAll(set);
        return lists;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrs = {10,-2,-12,3,-15,-12,2,-11,3,-12,9,12,0,-5,-4,-2,-7,-15,7,4,-5,-14,-15,-15,-4,10,9,-6,7,1,12,-6,14,-15,12,14,10,0,10,-10,3,4,-12,10,7,-9,-7,-15,-8,-15,-4,2,9,-4,3,-10,13,-3,-1,5,5,-4,-15,4,-11,4,-4,6,-11,-9,12,7,11,7,2,-5,13,10,-5,-10,3,7,0,-3,10,-12,2,9,-8,8,-9,13,12,13,-6,8,3,5,-9,7,12,10,-8,0,2,1,10,-7,-3,-10,-10,7,4,5,-11,-8,0,-2,-14,8,13,-8,-2,10,13};
        List<List<Integer>> list = solution.threeSum(arrs);
        System.out.println(list);
    }
}