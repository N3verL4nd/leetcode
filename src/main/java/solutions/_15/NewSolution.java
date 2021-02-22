package solutions._15;

import java.util.*;

public class NewSolution {
    private Map<Integer, Integer> map = new HashMap<>();

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<List<Integer>> lists = new ArrayList<>();

        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (!map.containsKey(-sum)) {
                    continue;
                }
                if ((-sum == nums[i] && map.get(nums[i]) < 2) || (-sum == nums[j] && map.get(nums[j]) < 2)) {
                    continue;
                }

                if (nums[i] == 0 && nums[j] == 0 && map.get(0) <= 2) {
                    continue;
                }

                int a = Math.min(Math.min(nums[i], nums[j]), -sum);
                int c = Math.max(Math.max(nums[i], nums[j]), -sum);
                set.add(new ArrayList<>(Arrays.asList(a, -a-c, c)));
            }
        }
        lists.addAll(set);
        return lists;
    }

    public static void main(String[] args) {
        NewSolution solution = new NewSolution();
        int[] arrs = {};
        List<List<Integer>> list = solution.threeSum(arrs);
        System.out.println(list);
    }
}
