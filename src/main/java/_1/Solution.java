package _1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsValue(target - nums[i])) {
                arr[1] = i;
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == (target - nums[i])) {
                        arr[1] = entry.getKey();
                    }
                }
                return arr;
            } else {
                map.put(i, nums[i]);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(arr, 9)));
    }
}