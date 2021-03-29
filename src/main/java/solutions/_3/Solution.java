package solutions._3;

import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        System.out.println(map);
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abba"));
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
}

/*

    left 维护当前滑动窗口的最左节点



    对于 "abba"

    i = 0 map[a] = 0 left = 0 max = 1
    i = 1 map[b] = 1 left = 0 max = 2
    i = 2 left = 2 map[b] = 2 max = 2
    i = 3 left = max(2, 1)=2 max = 2

 */

