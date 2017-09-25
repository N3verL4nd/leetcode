package solutions._169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. Majority Element
 */
public class Solution {

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

    public int majorityElement3(int[] nums) {
        int count = 1;
        int candidate = nums[0];
        for(int i = 1; i < nums.length; ++i)
        {
            if(count == 0)
            {
                candidate = nums[i];
            }
            if(candidate == nums[i]) {
                ++count;
            } else {
                count--;
            }
            System.out.println("count = " + count);
        }
        return candidate;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1,4,3,4,4};
//        System.out.println(solution.majorityElement1(arr));
//        System.out.println(solution.majorityElement2(arr));
        System.out.println(solution.majorityElement3(arr));
    }
}