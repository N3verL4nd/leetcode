package solutions._1;

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
            if (map.containsKey(target - nums[i])) {
                arr[0] = map.get(target - nums[i]);
                arr[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(arr, 9)));
    }
}