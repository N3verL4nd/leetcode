package solutions._169;

import java.util.HashMap;
import java.util.Map;

/**
 * 169. Majority Element
 */
public class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.replace(num, map.get(num) + 1);
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {8, 8, 7, 7, 7};
        System.out.println(solution.majorityElement(arr));
    }
}