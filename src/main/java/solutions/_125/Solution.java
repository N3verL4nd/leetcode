package solutions._125;

/**
 * 125. Valid Palindrome
 */
class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder(str.length);
        for (char c : str) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.append(c);
            }
        }
        String s1 = sb.toString();
        String s2 = sb.reverse().toString();
        return s1.equalsIgnoreCase(s2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(solution.isPalindrome("race a car"));
    }
}