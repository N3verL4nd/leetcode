package solutions._387;

import java.util.HashMap;
import java.util.Map;

/**
 * 387. First Unique Character in a String
 */
class Solution {
    public int firstUniqChar(String s) {
        char[] str = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < str.length; i++) {
            if (map.containsKey(str[i]) && map.get(str[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstUniqChar("leetcode"));
        System.out.println(solution.firstUniqChar("loveleetcode"));
    }
}