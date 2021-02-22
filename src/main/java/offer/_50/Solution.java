package offer._50;

import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public char firstUniqChar(String s) {
        if (s.length() <= 0) {
            return ' ';
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>(s.length());
        for (char aChar : chars) {
            if (map.containsKey(aChar)) {
                map.put(aChar, map.get(aChar) + 1);
            } else {
                map.put(aChar, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(new Solution().firstUniqChar("abaccdeff"));
        System.out.println(new Solution().firstUniqChar("leetcode"));
    }
}


/*

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

示例:

s = "abaccdeff"
返回 "b"

s = ""
返回 " "
 

限制：

0 <= s 的长度 <= 50000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */