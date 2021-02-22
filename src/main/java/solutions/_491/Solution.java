package solutions._491;

import java.util.*;

/**
 * 491. Increasing Subsequences
 */
class Solution {
    // 标记是否被访问过
    private boolean visited[];
    private Set<List<Integer>> result;
    private LinkedList<Integer> cur;
    private int len;


    private void DFS(int[] nums, int i) {
        // 遍历到最后一个元素则跳出
        if (i == nums.length) {
            cur.clear();
            return;
        }

        // 超过两个元素则加入结果集中
        if (len >= 2) {
            List<Integer> list = new ArrayList<>();
            Object[] objects = cur.toArray();
            for (int j = objects.length - 1; j >= 0; j--) {
                list.add((Integer) objects[j]);
            }
            result.add(list);
        }


        for (int j = i + 1; j < nums.length; j++) {
            if (!visited[j] && (nums[j] >= cur.peek())) {
                visited[j] = true;
                cur.push(nums[j]);
                len++;

                DFS(nums, j);

                // 回溯
                cur.pop();
                visited[j] = false;
                len--;
            }
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        visited = new boolean[nums.length];
        result = new HashSet<>();
        cur = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            visited[i] = true;
            cur.clear();
            cur.push(nums[i]);
            len = 1;
            DFS(nums, i);
        }
//        result.forEach(System.out::println);

        List<List<Integer>> lists = new ArrayList<>();
        lists.addAll(result);
        return lists;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
        List<List<Integer>> list = solution.findSubsequences(arr);
        System.out.println(list);
    }
}