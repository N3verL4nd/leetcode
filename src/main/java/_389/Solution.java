package _389;

public class Solution {
    public char findTheDifference(String s, String t) {
        if (s.length() == 0) {
            return t.charAt(0);
        }
        char ch = s.charAt(0);
        ch ^= t.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            ch ^= s.charAt(i);
            ch ^= t.charAt(i);
        }

        ch ^= t.charAt(t.length() - 1);
        return ch ;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findTheDifference("abcd", "abcde"));
        String s = "123";
    }
}