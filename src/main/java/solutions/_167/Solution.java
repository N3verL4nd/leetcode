package solutions._167;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 167. Two Sum II - Input array is sorted
 */
public class Solution {

    // 哈希表
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[0] = map.get(target - numbers[i]);
                result[1] = i + 1;
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }

    // 二分法
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (target - nums[i] > nums[mid]) {
                    left = mid + 1;
                } else if (target - nums[i] < nums[mid]) {
                    right = mid - 1;
                } else {
                    result[0] = i + 1;
                    result[1] = mid + 1;
                    return result;
                }
            }
        }
        return result;
    }

    // 双指针
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                result[0] = i + 1;
                result[1] = j + 1;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2, 7, 11, 15};
        System.out.println(Arrays.toString(solution.twoSum(arr, 9)));
    }
}