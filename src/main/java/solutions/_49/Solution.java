package solutions._49;

import java.util.*;

/**
 * 49. Group Anagrams
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }

        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> list = solution.groupAnagrams(strs);
        list.forEach(System.out::println);
    }
}