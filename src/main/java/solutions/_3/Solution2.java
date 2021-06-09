package solutions._3;

import java.util.HashMap;
import java.util.Map;


class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int j = map.get(s.charAt(i));
                for (int k = left; k <= j; k++) {
                    map.remove(s.charAt(k));
                }
                left = j + 1;
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, map.size());
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().lengthOfLongestSubstring("aabaab!bb"));
    }
}