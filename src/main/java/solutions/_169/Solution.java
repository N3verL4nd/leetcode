package solutions._169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. Majority Element
 */
public class Solution {

    // Hash
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > (nums.length / 2)) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > (nums.length / 2)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    // 中位数
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 多数投票算法
    public int majorityElement3(int[] nums) {
        int count = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (candidate == nums[i]) {
                ++count;
            } else {
                count--;
            }
        }
        return candidate;
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    // 快速选择算法
    private int QuickSelect(int[] arr, int left, int right, int k) {
        if (left < right) {
            int pivotPos = partition(arr, left, right);
            if (pivotPos == k - 1) {
                return arr[pivotPos];
            } else if (pivotPos > k - 1) {
                return QuickSelect(arr, left, pivotPos - 1, k);
            } else {
                return QuickSelect(arr, pivotPos + 1, right, k);
            }
        }
        return arr[left];
    }

    // 选择第
    public int majorityElement4(int[] nums) {
        int k = nums.length / 2;
        return QuickSelect(nums, 0, nums.length - 1, 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1, 4, 3, 4, 4};
//        System.out.println(solution.majorityElement1(arr));
//        System.out.println(solution.majorityElement2(arr));
//        System.out.println(solution.majorityElement3(arr));
        System.out.println(solution.majorityElement4(arr));
    }
}