package solutions._229;

import java.util.*;

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}