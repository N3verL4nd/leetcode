package solutions._242;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                int value = map.get(c);
                map.put(c, value + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                int value = map.get(c);
                if (value == 1) {
                    map.remove(c);
                } else {
                    map.put(c, value - 1);
                }
            } else {
                return false;
            }
        }
        return map.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isAnagram("anagram", "nagaram"));
        System.out.println(new Solution().isAnagram("rat", "car"));
    }
}