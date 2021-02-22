package solutions._344;

/**
 * 344. Reverse String
 */
public class Solution {
    public String reverseString(String s) {
        StringBuilder buffer = new StringBuilder();
        for (int i = s.length() - 1; i >= 0 ; i--) {
            buffer.append(s.charAt(i));
        }

        return buffer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseString("hello"));
    }
}