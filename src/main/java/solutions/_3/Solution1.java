package solutions._3;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 */
public class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>(s.length());
        char[] chars = s.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                if (set.contains(chars[j])) {
                    break;
                } else {
                    set.add(chars[j]);
                }
            }
            if (set.size() > max) {
                max = set.size();
            }
            set.clear();
        }
        return max;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring("a"));
        System.out.println(solution.lengthOfLongestSubstring("au"));
        System.out.println(solution.lengthOfLongestSubstring("jbpnbwwd"));
    }
}

/*

给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
示例1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
    请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。

示例 4:
输入: s = ""
输出: 0

提示：
0 <= s.length <= 5 * 104
s由英文字母、数字、符号和空格组成
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */